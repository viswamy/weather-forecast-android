package com.google.vswamy.weather_forecast_android;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.vswamy.weather_forecast_android.R;
import com.google.vswamy.weather_forecast_android.helpers.ImageSetterHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Next7Activity  extends Activity
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
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.next7);

        populateExtras();

        Gson gson = new Gson();
        JsonObject jsonObj = gson.fromJson(this.jsonData, JsonObject.class);
        Log.d("jsondata_vs", this.jsonData);
        JsonObject  dailyObj = jsonObj.get("daily").getAsJsonObject();

        JsonObject day1 = dailyObj.getAsJsonArray("data").get(0).getAsJsonObject();
        JsonObject day2 = dailyObj.getAsJsonArray("data").get(1).getAsJsonObject();
        JsonObject day3 = dailyObj.getAsJsonArray("data").get(2).getAsJsonObject();
        JsonObject day4 = dailyObj.getAsJsonArray("data").get(3).getAsJsonObject();
        JsonObject day5 = dailyObj.getAsJsonArray("data").get(4).getAsJsonObject();
        JsonObject day6 = dailyObj.getAsJsonArray("data").get(5).getAsJsonObject();
        JsonObject day7 = dailyObj.getAsJsonArray("data").get(6).getAsJsonObject();

        String x = "F";
        if(this.temperature.compareTo("Celsius") == 0)
            x = "C";

        ((TextView) findViewById(R.id.day1_tempinfo)).setText("Min: " +
                day1.get("temperatureMin").getAsString() + x + (char)0x00B0 + "|" + "Max: "
                + day1.get("temperatureMax").getAsString() + x + (char)0x00B0);

        ((TextView) findViewById(R.id.day2_tempinfo)).setText("Min: " +
                day2.get("temperatureMin").getAsString() + x + (char)0x00B0 + "|" + "Max: "
                + day2.get("temperatureMax").getAsString() + x + (char)0x00B0);

        ((TextView) findViewById(R.id.day3_tempinfo)).setText("Min: " +
                day3.get("temperatureMin").getAsString() + x + (char)0x00B0 + "|" + "Max: "
                + day3.get("temperatureMax").getAsString() + x + (char)0x00B0);

        ((TextView) findViewById(R.id.day4_tempinfo)).setText("Min: " +
                day4.get("temperatureMin").getAsString() + x + (char)0x00B0 + "|" + "Max: "
                + day4.get("temperatureMax").getAsString() + x + (char)0x00B0);

        ((TextView) findViewById(R.id.day5_tempinfo)).setText("Min: " +
                day5.get("temperatureMin").getAsString() + x + (char)0x00B0 + "|" + "Max: "
                + day5.get("temperatureMax").getAsString() + x + (char)0x00B0);

        ((TextView) findViewById(R.id.day6_tempinfo)).setText("Min: " +
                day6.get("temperatureMin").getAsString() + x + (char)0x00B0 + "|" + "Max: "
                + day6.get("temperatureMax").getAsString() + x + (char)0x00B0);

        ((TextView) findViewById(R.id.day7_tempinfo)).setText("Min: " +
                day7.get("temperatureMin").getAsString() + x + (char) 0x00B0 + "|" + "Max: "
                + day7.get("temperatureMax").getAsString() + x + (char) 0x00B0);

        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE , MMM dd");
        dateFormat.setTimeZone(TimeZone.getTimeZone("PST"));

        Date d1 = new Date(1000 * day1.get("time").getAsLong());
        Date d2 = new Date(1000 * day2.get("time").getAsLong());
        Date d3 = new Date(1000 * day3.get("time").getAsLong());
        Date d4 = new Date(1000 * day4.get("time").getAsLong());
        Date d5 = new Date(1000 * day5.get("time").getAsLong());
        Date d6 = new Date(1000 * day6.get("time").getAsLong());
        Date d7 = new Date(1000 * day7.get("time").getAsLong());

        ((TextView) findViewById(R.id.day1_dayinfo)).setText(dateFormat.format(d1));
        ((TextView) findViewById(R.id.day2_dayinfo)).setText(dateFormat.format(d2));
        ((TextView) findViewById(R.id.day3_dayinfo)).setText(dateFormat.format(d3));
        ((TextView) findViewById(R.id.day4_dayinfo)).setText(dateFormat.format(d4));
        ((TextView) findViewById(R.id.day5_dayinfo)).setText(dateFormat.format(d5));
        ((TextView) findViewById(R.id.day6_dayinfo)).setText(dateFormat.format(d6));
        ((TextView) findViewById(R.id.day7_dayinfo)).setText(dateFormat.format(d7));


        ((ImageView) findViewById(R.id.day1_image)).
                setImageResource(
                        ImageSetterHelper.findImage(
                                day1.get("icon").getAsString()));


        ((ImageView) findViewById(R.id.day2_image)).
                setImageResource(
                        ImageSetterHelper.findImage(
                                day2.get("icon").getAsString()));

        ((ImageView) findViewById(R.id.day3_image)).
                setImageResource(
                        ImageSetterHelper.findImage(
                                day3.get("icon").getAsString()));


        ((ImageView) findViewById(R.id.day4_image)).
                setImageResource(
                        ImageSetterHelper.findImage(
                                day4.get("icon").getAsString()));


        ((ImageView) findViewById(R.id.day5_image)).
                setImageResource(
                        ImageSetterHelper.findImage(
                                day5.get("icon").getAsString()));


        ((ImageView) findViewById(R.id.day6_image)).
                setImageResource(
                        ImageSetterHelper.findImage(
                                day6.get("icon").getAsString()));


        ((ImageView) findViewById(R.id.day7_image)).
                setImageResource(
                        ImageSetterHelper.findImage(
                                day7.get("icon").getAsString()));

        return;
    }

}
