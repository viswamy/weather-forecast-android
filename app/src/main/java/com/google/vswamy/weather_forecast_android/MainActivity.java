package com.google.vswamy.weather_forecast_android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;

import android.content.Intent;


public class MainActivity extends AppCompatActivity
{
    protected void registerEvents()
    {
        final Button button = (Button) findViewById(R.id.about);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent aboutIntent = new Intent(MainActivity.this, AboutActivity.class);
                MainActivity.this.startActivity(aboutIntent);
            }
        });


        final Button search = (Button) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener()
        {
            private String validate()
            {
                return "Swamy yay";
            }

            @Override
            public void onClick(View v)
            {
                String message = validate();
                TextView textView = (TextView) findViewById(R.id.error_message);
                if(message == null)
                {
                    // fetch data
                    textView.setVisibility(View.INVISIBLE);
                }
                else
                {
                    textView.setVisibility(View.VISIBLE);
                    textView.setText(message);
                }
            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        registerEvents();
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
}
