package com.google.vswamy.weather_forecast_android.helpers;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class WeatherHelperAsync extends AsyncTask<WeatherInput, Integer, String>
{
    public String getData(WeatherInput input) throws IOException
    {
        String url = "http://10.0.1.2/final/second.php?sa=__SA__&city=__CITY__&state=__STATE__&temperature=__TEMPERATURE__";
        url = url.replace("__SA__", input.streetAddress);
        url = url.replace("__CITY__", input.city);
        url = url.replace("__STATE__", input.state);
        url = url.replace("__TEMPERATURE__", input.temperature);

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        StringBuffer response = new StringBuffer();

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    @Override
    protected String doInBackground(WeatherInput... input)
    {
        String output = "";
        try
        {
            output = this.getData(input[0]);
            Log.d("data", output);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return output;
    }

}
