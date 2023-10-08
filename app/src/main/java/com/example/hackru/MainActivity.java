package com.example.hackru;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;


public class MainActivity extends AppCompatActivity {


    private User user;
    protected FrameLayout content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        SharedPreferences pf = getPreferences(MODE_PRIVATE);
//        boolean weather = pf.getBoolean(getString(R.string.weather_widget_active),true);
//        boolean quotes = pf.getBoolean(getString(R.string.motivational_quote_widget_active), true);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.weatherFragmentContainerView, WeatherFragment.class, null)
                    .add(R.id.quotesFragmentContainerView, QuotesFragment.class, null)
                    .commit();
        }
    }
}