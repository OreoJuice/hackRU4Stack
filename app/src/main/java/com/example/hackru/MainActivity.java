package com.example.hackru;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;


public class MainActivity extends AppCompatActivity {


    private User user;
    protected FrameLayout content;
    private Button toSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toSettings = (Button) findViewById(R.id.buttonSettings);
        String questions = "MY_QUOTES";
        String weather = "MY_WEATHER";
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
        else{
            SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
            FragmentManager fragmentManager = MainActivity.this.getSupportFragmentManager();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            boolean weather_enabled = (boolean)sharedPref.getBoolean(getString(R.string.weather_widget_active), true);
            boolean quotes_enabled = (boolean)sharedPref.getBoolean(getString(R.string.motivational_quote_widget_active), true);
            List<Fragment> fragments = fragmentManager.getFragments();
            if(fragments != null){
                for(Fragment fragment : fragments){
                    int fragID = fragment.getId();
                    if(fragID == R.id.weatherFragmentContainerView && !weather_enabled){
                        ft.hide(fragment);
                    }
                    if(fragID == R.id.quotesFragmentContainerView && !quotes_enabled){
                        ft.hide(fragment);
                    }

                }
            }

        }


        toSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_settings);
            }
        });
    }
}