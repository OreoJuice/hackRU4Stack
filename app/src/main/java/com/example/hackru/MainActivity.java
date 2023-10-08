package com.example.hackru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.google.gson.Gson;


public class MainActivity extends AppCompatActivity {


    private User user;
    public static final String USER = "user";
    protected FrameLayout content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences pf = getPreferences(MODE_PRIVATE);
        boolean weather = pf.getBoolean(getString(R.string.weather_widget_active),true);
        boolean quotes = pf.getBoolean(getString(R.string.motivational_quote_widget_active), true);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragmentContainerView2, WeatherFragment.class, null)
                    .commit();
        }
    }
}