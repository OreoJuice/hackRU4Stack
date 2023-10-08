package com.example.hackru;

import static android.content.Context.MODE_PRIVATE;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;

public class WeatherFragment extends Fragment {
    private final String KEY = "08ab2361e03f6f8d20395208efe509d4";
    private double lon;
    private double lat;
    private String url;
    private double temp;
    private double temp_min;
    private double temp_max;
    private String condition;
    private String icon;

    private Weather current;

    private ImageView iconImageView;
    private TextView temperatureTextView;
    private TextView locationTextView;
    private TextView highTextView;
    private TextView lowTextView;
    private String location;
    private RequestQueue queue;

    public WeatherFragment(){
        super(R.layout.fragment_weather);
    }

    private void FindUserWeather() {
        LocationManager lm = (LocationManager) getActivity().getApplicationContext().getSystemService(Context.LOCATION_SERVICE);

        if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions( getActivity(), new String[] {  android.Manifest.permission.ACCESS_COARSE_LOCATION  }, 10);
        } else { 
            Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            lon = location.getLongitude();
            lat = location.getLatitude();
            getWeather(lon, lat);
        }

    }

    private void getWeather(double lon, double lat) { //Gets weather from given lon and lat, stores in premade variables of fragment
        queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        url = "https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon="+ lon + "&appid=" + KEY
        + "&units=imperial";
//        Log.d("arst", "getWeather");
//        Log.d("arst", url);
        //Make JSON GET request and call testJsonToVar to parse the data
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
//            Log.d("arst", response.toString());
            testJsonToVar(response); //PARSING OF JSON FILE TO WEATHER
        }, error -> {});
        queue.add(jsonObjectRequest);
    }

    private void testJsonToVar(JSONObject input){
//        Log.d("arst", "testJsonToVar");
        try {
            //Get JSON properties
            temp = input.getJSONObject("main").getDouble("temp");
            temp_min = input.getJSONObject("main").getDouble("temp_min");
            temp_max = input.getJSONObject("main").getDouble("temp_max");
            location = input.getString("name");
            condition = ((JSONObject)input.getJSONArray("weather").get(0)).getString("main");
            icon = ((JSONObject)input.getJSONArray("weather").get(0)).getString("icon");
            String iconURL = "https://openweathermap.org/img/wn/" + icon + "@2x.png";
            current = new Weather(temp, condition, iconURL, temp_min, temp_max);
            //Convert from Object -> JSON and store in SharedPreferences
            SharedPreferences mPrefs = getActivity().getPreferences(MODE_PRIVATE);
            SharedPreferences.Editor prefsEditor = mPrefs.edit();
            Gson gson = new Gson();
            String json = gson.toJson(current);
            prefsEditor.putString("MyWeather", json);
            prefsEditor.commit();
            Log.d("arst", current.toString());
            fillData(current);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void fillData(Weather current) {
        temperatureTextView.setText(String.valueOf(current.getTemperature()) + "°F");
        highTextView.setText(String.valueOf(current.getMax() + "°F"));
        lowTextView.setText(String.valueOf(current.getMin() + "°F"));
        locationTextView.setText(location);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        iconImageView = (ImageView)view.findViewById(R.id.iconImageView);
        temperatureTextView = (TextView)view.findViewById(R.id.temperatureTextView);
        locationTextView = (TextView)view.findViewById(R.id.locationTextView);
        highTextView = (TextView)view.findViewById(R.id.highTextView);
        lowTextView = (TextView)view.findViewById(R.id.lowTextView);

        try {
            //Get Weather object from SharedPreferences
            SharedPreferences mPrefs = getActivity().getPreferences(MODE_PRIVATE);
            Gson gson = new Gson();
            String json = mPrefs.getString("MyWeather", "");
            Weather obj = gson.fromJson(json, Weather.class);
            if(obj != null) {
                String weatherIcon = obj.getIconURL();
                Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(weatherIcon).getContent());
                iconImageView.setImageBitmap(bitmap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Get User Location
        FindUserWeather();
//        Log.d("arst", current.toString());
        if (current == null) {
            return;
        }
    }
}