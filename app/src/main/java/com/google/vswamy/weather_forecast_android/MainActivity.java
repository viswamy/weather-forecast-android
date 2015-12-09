package com.google.vswamy.weather_forecast_android;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Spinner;

import android.content.Intent;

import com.google.gson.Gson;
import com.google.vswamy.weather_forecast_android.helpers.WeatherHelper;
import com.google.vswamy.weather_forecast_android.helpers.WeatherInput;

import android.util.Log;

import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;


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

            @Override
            public void onClick(View v)
            {
                WeatherInput input = new WeatherInput();
                input.streetAddress = ((EditText) findViewById(R.id.street_input)).getText().toString();
                input.city = ((EditText) findViewById(R.id.city_input)).getText().toString();
                input.state = ((Spinner) findViewById(R.id.state_input)).getSelectedItem().toString();
                RadioButton radioButton = (RadioButton) findViewById(
                        ((RadioGroup) findViewById(R.id.radioTemperature)).getCheckedRadioButtonId())
                        ;
                input.temperature = radioButton.getText().toString();

                String message = validate(input);
                TextView textView = (TextView) findViewById(R.id.error_message);
                if(message == null)
                {
                    textView.setVisibility(View.INVISIBLE);
                    try
                    {
                        WeatherHelper.getWeatherData(input);
                    }
                    catch (ParserConfigurationException e)
                    {
                        e.printStackTrace();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    catch (SAXException e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                {
                    textView.setVisibility(View.VISIBLE);
                    textView.setText(message);
                }
            }
            public String validate(WeatherInput input)
            {
                if(input.streetAddress == null || input.streetAddress.trim().compareTo("") == 0)
                {
                    return "Street Address is Empty";
                }

                if(input.city == null || input.city.trim().compareTo("") == 0)
                {
                    return "City is Empty";
                }

                if(input.state == null || input.state.trim().compareTo("") == 0)
                {
                    return "State is Empty";
                }

                return null;
            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

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
