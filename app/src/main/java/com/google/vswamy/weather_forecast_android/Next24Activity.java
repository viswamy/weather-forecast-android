package com.google.vswamy.weather_forecast_android;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.vswamy.weather_forecast_android.R;

public class Next24Activity  extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.next24tab);

        init();
    }

    private void init() {
        TableLayout stk = (TableLayout) findViewById(R.id.table_next24);
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
        temperature.setText(" Temperature ");
        temperature.setTextColor(Color.BLACK);
        temperature.setTextSize(25);
        temperature.setGravity(Gravity.CENTER);
        temperature.setPadding(10, 0, 10, 0);
        header.addView(temperature);
        stk.addView(header);

        for (int i = 0; i < 24; i++) {
            TableRow row_data = new TableRow(this);
            TextView time_value = new TextView(this);
            if(i%2==0)
            {
                row_data.setBackgroundColor(Color.LTGRAY);
            }
            time_value.setText("" + i);
            time_value.setTextColor(Color.BLACK);
            time_value.setGravity(Gravity.CENTER);
            time_value.setTextSize(25);
            row_data.addView(time_value);

            ImageView summary_image = new ImageView(this);
            summary_image.setImageResource(R.mipmap.ic_launcher);
            row_data.addView(summary_image);

            TextView temperature_value = new TextView(this);
            temperature_value.setText("Rs." + i);
            temperature_value.setTextColor(Color.BLACK);
            temperature_value.setGravity(Gravity.CENTER);
            temperature_value.setTextSize(25);
            row_data.addView(temperature_value);
            stk.addView(row_data);
        }

    }


}