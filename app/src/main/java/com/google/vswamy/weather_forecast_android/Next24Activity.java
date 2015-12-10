package com.google.vswamy.weather_forecast_android;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.vswamy.weather_forecast_android.R;
import com.google.vswamy.weather_forecast_android.helpers.ImageSetterHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Next24Activity  extends Activity implements View.OnClickListener
{
    String jsonData;
    String streetAddress;
    String city;
    String temperature;
    String state;

    TableLayout stk;

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
        setContentView(R.layout.next24tab);
        populateExtras();

        stk = (TableLayout) findViewById(R.id.table_next24);

        init();
    }

    private void init() {

        TableRow header = new TableRow(this);
        header.setBackgroundColor(Color.rgb(135,206,250));

        TextView time = new TextView(this);
        time.setText(" Time ");
        time.setTextColor(Color.BLACK);
        time.setTextSize(25);
        time.setGravity(Gravity.CENTER);
        time.setPadding(10, 0, 10, 0);
        header.addView(time);

        TextView summary = new TextView(this);
        summary.setText("Summary");
        summary.setTextColor(Color.BLACK);
        summary.setTextSize(25);
        summary.setPadding(10, 0, 10, 0);
        summary.setGravity(Gravity.CENTER);
        header.addView(summary);

        TextView temperature = new TextView(this);
        temperature.setText(" Temp " + ((char)0x00B0) +((this.temperature.compareTo("Celsius") == 0) ? "C" : "F"));
        temperature.setTextColor(Color.BLACK);
        temperature.setTextSize(25);
        temperature.setGravity(Gravity.CENTER);
        temperature.setPadding(10, 0, 10, 0);
        header.addView(temperature);

        //Add header to table layout!
        stk.addView(header);



        Gson gson = new Gson();
        JsonObject jsonObj = gson.fromJson(this.jsonData, JsonObject.class);
        JsonObject  hourlyObj = jsonObj.get("hourly").getAsJsonObject();

        for (int i = 0; i < 24; i++) {
            JsonObject curr = hourlyObj.getAsJsonArray("data").get(i).getAsJsonObject();

            TableRow row_data = new TableRow(this);
            TextView time_value = new TextView(this);

            if(i%2==0)
            {
                row_data.setBackgroundColor(Color.LTGRAY);
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
            dateFormat.setTimeZone(TimeZone.getTimeZone("PST"));
            Date d1 = new Date(1000 * curr.get("time").getAsLong());

            time_value.setText(dateFormat.format(d1));

            time_value.setTextColor(Color.BLACK);
            time_value.setGravity(Gravity.CENTER);
            time_value.setTextSize(25);
            row_data.addView(time_value);

            ImageView summary_image = new ImageView(this);
            summary_image.setImageResource(ImageSetterHelper.findImage(curr.get("icon").getAsString()));
            row_data.addView(summary_image);

            TextView temperature_value = new TextView(this);
            temperature_value.setText(curr.get("temperature").getAsString());
            temperature_value.setTextColor(Color.BLACK);
            temperature_value.setGravity(Gravity.CENTER);
            temperature_value.setTextSize(25);
            row_data.addView(temperature_value);
            stk.addView(row_data);
        }

        Button b = new Button(this);
        b.setText("Load More");
        b.setOnClickListener(this);
        stk.addView(b);
    }


    @Override
    public void onClick(View v)
    {
        stk.removeView(v);
        Gson gson = new Gson();
        JsonObject jsonObj = gson.fromJson(this.jsonData, JsonObject.class);
        JsonObject  hourlyObj = jsonObj.get("hourly").getAsJsonObject();

        for (int i = 24; i < 48; i++) {
            JsonObject curr = hourlyObj.getAsJsonArray("data").get(i).getAsJsonObject();

            TableRow row_data = new TableRow(this);
            TextView time_value = new TextView(this);

            if(i%2==0)
            {
                row_data.setBackgroundColor(Color.LTGRAY);
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
            dateFormat.setTimeZone(TimeZone.getTimeZone("PST"));
            Date d1 = new Date(1000 * curr.get("time").getAsLong());

            time_value.setText(dateFormat.format(d1));

            time_value.setTextColor(Color.BLACK);
            time_value.setGravity(Gravity.CENTER);
            time_value.setTextSize(25);
            row_data.addView(time_value);

            ImageView summary_image = new ImageView(this);
            summary_image.setImageResource(ImageSetterHelper.findImage(curr.get("icon").getAsString()));
            row_data.addView(summary_image);

            TextView temperature_value = new TextView(this);
            temperature_value.setText(curr.get("temperature").getAsString());
            temperature_value.setTextColor(Color.BLACK);
            temperature_value.setGravity(Gravity.CENTER);
            temperature_value.setTextSize(25);
            row_data.addView(temperature_value);
            stk.addView(row_data);
        }
    }
}