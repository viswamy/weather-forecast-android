package com.google.vswamy.weather_forecast_android;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.hamweather.aeris.maps.AerisMapView;
import com.hamweather.aeris.tiles.AerisTile;

public class MyMapActivity extends Activity implements OnMapReadyCallback
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        populateExtras();
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.my_map);
        mapFragment.getMapAsync(this);
        AerisMapView aerisMapView = (AerisMapView) findViewById(R.id.my_map);
        aerisMapView.addLayer(AerisTile.CURRENT_DEWPOINT);
        aerisMapView.addLayer(AerisTile.CURRENT_HUMIDITY);
    }

    @Override
    public void onMapReady(GoogleMap map) {

        Gson gson = new Gson();
        JsonObject jsonObj = gson.fromJson(this.jsonData, JsonObject.class);
        Log.d("json_data", this.jsonData);
        Log.d("latitude", jsonObj.get("latitude").getAsString());
        Log.d("longitude", jsonObj.get("longitude").getAsString());
        LatLng latLng = new LatLng(jsonObj.get("latitude").getAsDouble(), jsonObj.get("longitude").getAsDouble());

        //LatLng latLng = new LatLng(34.0283358,-118.2794449);

        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));

        map.addMarker(new MarkerOptions()
                .title("This is title")
                .snippet("This is Snippet")
                .position(latLng));
    }
}
