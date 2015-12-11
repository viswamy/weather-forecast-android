package com.google.vswamy.weather_forecast_android.helpers;

import com.google.vswamy.weather_forecast_android.R;

public class ImageSetterHelper
{
    public static String findImageUrl(String icon)
    {
        String prefix = "http://cs-server.usc.edu:45678/hw/hw8/images/";
        String suffix = "";
        switch (icon)
        {
            case "clear-day":
                suffix = "clear.png";

            case "clear-night":
                suffix = "clear_night.png";

            case "rain":
                suffix = "rain.png";

            case "snow":
                suffix = "snow.png";

            case "sleet":
                suffix = "sleet.png";

            case "wind":
                suffix = "wind.png";

            case "fog":
                suffix  = "fog.png";

            case "cloudy":
                suffix = "cloudy.png";

            case "partly-cloudy-day":
                suffix = "cloud_day.png";

            case "partly-cloudy-night":
                suffix = "cloud_night.png";
        }

        return prefix + suffix;
    }
    public static int findImage(String icon)
    {
        switch (icon) {
            case "clear-day":
                return R.mipmap.clear;

            case "clear-night":
                return R.mipmap.clear_night;

            case "rain":
                return R.mipmap.rain;

            case "snow":
                return R.mipmap.snow;

            case "sleet":
                return R.mipmap.sleet;

            case "wind":
                return R.mipmap.wind;

            case "fog":
                return R.mipmap.fog;

            case "cloudy":
                return R.mipmap.cloudy;

            case "partly-cloudy-day":
                return R.mipmap.cloud_day;

            case "partly-cloudy-night":
                return R.mipmap.cloud_night;

        }
        return R.mipmap.clear;
    }
}
