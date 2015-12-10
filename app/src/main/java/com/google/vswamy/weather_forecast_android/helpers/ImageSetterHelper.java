package com.google.vswamy.weather_forecast_android.helpers;

import com.google.vswamy.weather_forecast_android.R;

public class ImageSetterHelper
{
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
