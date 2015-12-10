package com.google.vswamy.weather_forecast_android;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.vswamy.weather_forecast_android.helpers.ImageSetterHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class MyMapActivity extends Activity
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

        
    }

}
