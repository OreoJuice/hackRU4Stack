package com.example.hackru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;


public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPref;
    SharedPreferences.Editor sharedPrefEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO move editor object into only the if statement (no editing done otherwise)
        sharedPref = getApplicationContext().getSharedPreferences(getString(R.string.shared_prefs), Context.MODE_PRIVATE);
        sharedPrefEditor = sharedPref.edit();

        if (sharedPref.getString(getString(R.string.already_booted), "").isEmpty()) {
            //First Time, set prefs, boot up settings w/ dialog
            //TODO define default values for sharedpreferences
            sharedPrefEditor.putString(getString(R.string.already_booted), "true");
            sharedPrefEditor.putBoolean(getString(R.string.weather_widget_active), true);
            sharedPrefEditor.putBoolean(getString(R.string.celsius), true);
            sharedPrefEditor.putBoolean(getString(R.string.motivational_quote_widget_active), true);
            //TODO fix default font
            sharedPrefEditor.putString(getString(R.string.font_family), "kanit");
            sharedPrefEditor.apply();

            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void deleteAllSharedPrefs() {
        sharedPrefEditor.remove(getString(R.string.already_booted));
        sharedPrefEditor.remove(getString(R.string.user_name));
        sharedPrefEditor.remove(getString(R.string.weather_widget_active));
        sharedPrefEditor.remove(getString(R.string.celsius));
        sharedPrefEditor.remove(getString(R.string.motivational_quote_widget_active));
        sharedPrefEditor.remove(getString(R.string.font_family));
    }
}