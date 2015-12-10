package com.google.vswamy.weather_forecast_android;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


public class ResultActivity extends AppCompatActivity implements View.OnClickListener
{
    String jsonData;
    String streetAddress;
    String city;
    String temperature;
    String state;

    private void populateExtras()
    {
        this.jsonData = getIntent().getExtras().getString("jsonData");
        this.streetAddress = getIntent().getExtras().getString("streetAddress");
        this.city = getIntent().getExtras().getString("city");
        this.temperature = getIntent().getExtras().getString("temperature");
        this.state = getIntent().getExtras().getString("state");
        return;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d("Result Activity", "true");
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_result);

        populateExtras();
        Log.d("temperature_unit_ra", this.temperature);

        displayWeatherInformation();

        ShareButton imageButton = (ShareButton) findViewById(R.id.fb_icon);
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("https://developers.facebook.com"))
                .setContentTitle("this is title")
                .setImageUrl(Uri.parse("https://icons.iconarchive.com/icons/yootheme/social-bookmark/256/social-facebook-button-blue-icon.png"))
                .setContentDescription("this is description")
                .build();
        ShareButton shareButton = (ShareButton)findViewById(R.id.fb_icon);
        shareButton.setShareContent(content);

        //Register Events
        Button detailsButton = (Button) findViewById(R.id.detail);
        detailsButton.setOnClickListener(this);

    }

    private void displayWeatherInformation()
    {
        TextView temperatureDegree = (TextView) findViewById(R.id.temp_degree); //degree F or C
        TextView temperature = (TextView) findViewById(R.id.temperature); //degree F or C
        TextView precipitation = (TextView) findViewById(R.id.precipitation); //Precipitation
        TextView rain = (TextView) findViewById(R.id.chance_rain); //Rain
        TextView windSpeed = (TextView) findViewById(R.id.windspeed); //Wind Speed
        TextView dew = (TextView) findViewById(R.id.dew); //Dew
        TextView humidity = (TextView) findViewById(R.id.humidity); //Humidity
        TextView visibility = (TextView) findViewById(R.id.visibility); //Visibility
        TextView sunrise = (TextView) findViewById(R.id.sunrise); //Sunrise
        TextView sunset = (TextView) findViewById(R.id.sunset); //Sunset
        TextView detail =  (TextView) findViewById(R.id.detail); //detail
        TextView summary =  (TextView) findViewById(R.id.summary); //detail

        TextView lowHighTemperature = (TextView) findViewById(R.id.temp_lowhigh);

        Gson gson = new Gson();
        JsonObject jsonObj = gson.fromJson(this.jsonData, JsonObject.class);
        JsonObject currentlyObj = jsonObj.get("currently").getAsJsonObject();
        JsonObject  dailyObj = jsonObj.get("daily").getAsJsonObject();
        JsonObject dailyObj0 = dailyObj.getAsJsonArray("data").get(0).getAsJsonObject();

        summary.setText(currentlyObj.get("summary").getAsString() + " in " + this.city + "," + this.state);
        String t_temperatureDegree = "N/A";

        if(this.temperature.compareTo("Celsius") == 0)
            t_temperatureDegree = "C";
        else
            t_temperatureDegree = "F";
        temperatureDegree.setText((char)0x00B0 + t_temperatureDegree);

        Double t_temperature = currentlyObj.get("temperature") != null ? currentlyObj.get("temperature").getAsDouble() : null;
        if(t_temperature != null)
            temperature.setText(String.format("%.2f",t_temperature));
        else
            temperature.setText("N/A");

        Double t_precipitation = currentlyObj.get("precipIntensity") != null ? currentlyObj.get("precipIntensity").getAsDouble() : null;
        if(t_precipitation != null)
            precipitation.setText(String.format("%.2f", t_precipitation));
        else
            precipitation.setText("N/A");

        Double t_rain = currentlyObj.get("precipProbability") != null ? currentlyObj.get("precipProbability").getAsDouble() * 100 : null;
        if(t_rain != null)
           rain.setText(String.format("%.2f ", t_rain) + "%");
        else
            rain.setText("N/A");

        Double t_windSpeed = currentlyObj.get("windSpeed") != null ? currentlyObj.get("windSpeed").getAsDouble() : null;
        if(t_windSpeed != null)
            windSpeed.setText(String.format("%.2f",t_windSpeed) + (this.temperature.compareTo("Celsius") == 0 ? "m/s" : "mph"));
        else
            windSpeed.setText("N/A");

        Double t_dew = currentlyObj.get("dewPoint") != null ? currentlyObj.get("dewPoint").getAsDouble() : null;
        if(t_dew != null)
            dew.setText(String.format("%.2f", t_dew) + (this.temperature.compareTo("Celsius") == 0 ? "C" : "F"));
        else
            dew.setText("N/A");

        Double t_humidity = currentlyObj.get("humidity") != null ? currentlyObj.get("humidity").getAsDouble() : null;
        if(t_humidity != null)
            humidity.setText(String.format("%.2f", t_humidity) + "%");
        else
            humidity.setText("N/A");

        Double t_visibility = currentlyObj.get("visibility") != null ? currentlyObj.get("visibility").getAsDouble() : null;
        if(t_visibility != null)
            visibility.setText(String.format("%.2f", t_visibility) + (this.temperature.compareTo("Celsius") == 0 ? "km" : "mi"));
        else
            visibility.setText("N/A");

        Date sr = new Date(dailyObj0.get("sunriseTime").getAsLong() * 1000);

        Date ss = new Date(dailyObj0.get("sunsetTime").getAsLong() * 1000);
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
        dateFormat.setTimeZone(TimeZone.getTimeZone("PST"));

        sunrise.setText(dateFormat.format(sr));
        sunset.setText(dateFormat.format(ss));

        int lowT = (int) dailyObj0.get("temperatureMin").getAsDouble();
        int highT = (int) dailyObj0.get("temperatureMax").getAsDouble();
        Log.d("lowT", "" + lowT);
        Log.d("highT", "" + highT);
        lowHighTemperature.setText("L:" + lowT +  (char)0x00B0 + " | " + "H:" + highT + (char)0x00B0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void putExtras(Intent i)
    {
        i.putExtra("jsonData", this.jsonData);
        i.putExtra("streetAddress", this.streetAddress);
        i.putExtra("city", this.city );
        i.putExtra("state", this.state);
        i.putExtra("temperature", this.temperature);
        return;
    }
    @Override
    public void onClick(View v)
    {
        Log.d("vs_View id", v.getId()+"");

        if(v.getId() == R.id.detail)
        {
            Intent i = new Intent(ResultActivity.this, DetailsActivity.class);
            putExtras(i);
            ResultActivity.this.startActivity(i);
        }
        else if(v.getId() == R.id.map)
        {
            Intent i = new Intent(ResultActivity.this, DetailsActivity.class);
            putExtras(i);
            ResultActivity.this.startActivity(i);
        }
        return;
    }
}
