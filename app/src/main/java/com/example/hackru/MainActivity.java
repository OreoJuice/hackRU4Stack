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
    public static final String USER = "user";
    protected FrameLayout content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        LinearLayout layout = new LinearLayout(this);
        layout.setId(R.id.linear_layout);
        FragmentManager fragMan = getFragmentManager();
        FragmentTransaction fragTransaction = fragMan.beginTransaction();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_weather, WeatherFragment.newInstance("Weather1", "Weather2"))
                    .replace(R.id.fragment_container_quotes, QuotesFragment.newInstance("Quote1", "Quote2"))
                    .commitNow();
        }



    }

    public String serializeUser(User myUser){
        SharedPreferences sp = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor pe = sp.edit();
        Gson gson = new Gson();
        String jUser = gson.toJson(myUser);
        pe.putString("MyUser", jUser);
        pe.apply();
        return jUser;
    }

    public User deserializeSessionFromJson() {
        Gson gson = new Gson();
        SharedPreferences sp = getPreferences(MODE_PRIVATE);
        String jUser = sp.getString("MyUser", null);
        return gson.fromJson(jUser, User.class);
    }
}