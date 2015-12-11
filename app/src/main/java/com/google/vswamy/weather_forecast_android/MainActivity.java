package com.google.vswamy.weather_forecast_android;

import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import android.content.Intent;

import com.google.vswamy.weather_forecast_android.helpers.WeatherInput;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


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
            private String validate(WeatherInput input)
            {
                if(input.streetAddress == null || input.streetAddress.trim().compareTo("") == 0)
                {
                    return "Street Address is Empty";
                }
                if(input.city == null || input.city.trim().compareTo("") == 0)
                {
                    return "City Address is Empty";
                }
                if(input.state == null || input.state.trim().compareTo("") == 0)
                {
                    return "State Address not selected";
                }
                if(input.temperature == null || input.temperature.trim().compareTo("") == 0)
                {
                    return "Temperature Address is Empty";
                }
                return null;
            }

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
                Log.d("temperature_unit", input.temperature);
                String message = validate(input);

                TextView textView = (TextView) findViewById(R.id.error_message);
                if(message == null)
                {
                    textView.setVisibility(View.INVISIBLE);
                    String output = null;
                    //new WeatherHelperAsync().execute(input);
                    try
                    {
                        String url = "http://weather-vswamy.elasticbeanstalk.com/second.php?sa=__SA__&city=__CITY__&state=__STATE__&temperature=__TEMPERATURE__";

                        url = url.replace("__SA__", input.streetAddress);
                        url = url.replace("__CITY__", input.city);
                        url = url.replace("__STATE__", input.state);
                        url = url.replace("__TEMPERATURE__", input.temperature);
                        url = url.replace(" ", "%20");

                        URL obj = new URL(url);
                        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                        Log.d("vs_url", url);
                        BufferedReader in = new BufferedReader(
                                new InputStreamReader(con.getInputStream()));
                        StringBuffer response = new StringBuffer();

                        String inputLine;
                        while ((inputLine = in.readLine()) != null)
                        {
                            response.append(inputLine);
                        }
                        in.close();

                        output = response.toString();
                        Log.d("vs_output", output);
                        Intent i = new Intent(MainActivity.this, ResultActivity.class);
                        i.putExtra("jsonData", output);
                        i.putExtra("streetAddress", input.streetAddress);
                        i.putExtra("state", input.state);
                        i.putExtra("temperature", input.temperature);
                        i.putExtra("city", input.city);
                        startActivity(i);
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();;
                    }

                }
                else
                {
                    textView.setVisibility(View.VISIBLE);
                    textView.setText(message);
                }
            }
        });

        final Button clear = (Button) findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ((EditText)findViewById(R.id.street_input)).setText("");
                ((EditText)findViewById(R.id.city_input)).setText("");
                ((Spinner) findViewById(R.id.state_input)).setSelection(0);
                ((RadioButton)findViewById(R.id.fahrenheit)).setChecked(true);
            }
        });
    }

    protected void temp()
    {
        //No Initialization...
        /*
        ((EditText) findViewById(R.id.street_input)).setText("720W 27TH ST");
        ((EditText) findViewById(R.id.city_input)).setText("Los Angeles");
        ((Spinner) findViewById(R.id.state_input)).setSelection(2);
        ((RadioButton)findViewById(R.id.fahrenheit)).setChecked(true);
        */
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d("application starting", "true");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        temp();
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
