package com.google.vswamy.weather_forecast_android;

import android.os.Bundle;
import android.app.TabActivity;

import android.content.Intent;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class DetailsActivity extends TabActivity
{
    String jsonData;
    String streetAddress;
    String city;
    String temperature;
    String state;

    private void putExtras(Intent i)
    {
        i.putExtra("jsonData", this.jsonData);
        i.putExtra("streetAddress", this.streetAddress);
        i.putExtra("city", this.city );
        i.putExtra("state", this.state);
        i.putExtra("temperature", this.temperature);
    }

    private void populateExtras()
    {
        this.jsonData = getIntent().getExtras().getString("jsonData");
        this.streetAddress = getIntent().getExtras().getString("streetAddress");
        this.city = getIntent().getExtras().getString("city");
        this.temperature = getIntent().getExtras().getString("temperature");
        this.state = getIntent().getExtras().getString("state");
    }
    /**
     * Called when the activity is first created.
     */
    @SuppressWarnings("deprecation")
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        populateExtras();

        // create the TabHost that will contain the Tabs
        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);


        TabSpec tab1 = tabHost.newTabSpec("First Tab");
        TabSpec tab2 = tabHost.newTabSpec("Second Tab");

        tab1.setIndicator("Next 24 Hours");
        Intent i1 = new Intent(this, Next24Activity.class);
        putExtras(i1);
        tab1.setContent(i1);

        tab2.setIndicator("Next 7 Days");
        Intent i2 = new Intent(this, Next7Activity.class);
        putExtras(i2);
        tab2.setContent(i2);

        tabHost.addTab(tab1);
        tabHost.addTab(tab2);

    }

    @Override
    public void onStart()
    {
        super.onStart();
    }

    @Override
    public void onStop()
    {
        super.onStop();
    }
}
