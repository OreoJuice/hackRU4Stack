package com.example.hackru;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link //WeatherFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeatherFragment extends Fragment {
    final String KEY = "08ab2361e03f6f8d20395208efe509d4";
    double lon;
    double lat;
    String url;
    int temp;
    TextView textView;

    View view;



    public WeatherFragment(){
        super(R.layout.fragment_weather);
        if(isAdded()) {
            Log.d("mine","shutyu bitchass up troy");
            FindUserWeather();
//            textView = (TextView) view.findViewById(R.id.temperatureTest);
//            textView.setText("" + temp);
        }else{
            Log.d("mine","shutyu bitchass up chiyo");
        }

    }

    public static Fragment newInstance() {
        return new WeatherFragment();
    }

    private void FindUserWeather() {
        LocationManager lm = (LocationManager) getActivity().getApplicationContext().getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            lon = location.getLongitude();
            lat = location.getLatitude();

            getWeather(lon, lat);
        }

    }

    private void getWeather(double lon, double lat) { //Gets weather from given lon and lat, stores in premade variables of fragment
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        url = "https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon="+ lon + "&appid=" + KEY
        + "&units=imperial";

        JSONObject request = new JSONObject();

        //REQUESTING JSON AND PARSING
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, request, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        testJsonToVar(response); //PARSING OF JSON FILE TO WEATHER TODO:change from test to weather class
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });
    }




    private void testJsonToVar(JSONObject input){
    try {
        temp = input.getInt("weather.id");
        return;
    }catch(Exception e){
        System.out.println(e);
    }
        temp = -1;
    }

    private Weather jsonToWeather(JSONObject input){
        //TODO:implement turning a json object retrieved from API into a weather object
        return null;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_weather, container, false);
        return view;
    }
}