package com.example.hackru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Spinner;
import android.widget.Switch;

import static com.google.android.material.internal.ContextUtils.getActivity;

public class SettingsActivity extends AppCompatActivity{

    private Switch weatherWidgetSwitch;
    private Switch celsiusSwitch;
    private Switch motivationalQuotesWidget;
    private Spinner fontFamilySpinner;

    private SharedPreferences sharedPref;
    private SharedPreferences.Editor sharedPrefEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        sharedPref = getApplicationContext().getSharedPreferences(getString(R.string.shared_prefs), Context.MODE_PRIVATE);
        sharedPrefEditor = sharedPref.edit();

        instantiateViewObjects();
    }

    private void instantiateViewObjects() {
        //Weather Widget
        weatherWidgetSwitch = (Switch)findViewById(R.id.weatherWidgetSwitch);
        celsiusSwitch = (Switch)findViewById(R.id.celsiusSwitch);
        weatherWidgetSwitch.setChecked(sharedPref.getBoolean(getString(R.string.weather_widget_active), true));
        celsiusSwitch.setChecked(sharedPref.getBoolean(getString(R.string.celsius), true));

        //Motivational Quotes Widget
        motivationalQuotesWidget = (Switch)findViewById(R.id.motivationalQuotesWidget);
        fontFamilySpinner = (Spinner)findViewById(R.id.fontFamilySpinner);
        motivationalQuotesWidget.setChecked(sharedPref.getBoolean(getString(R.string.motivational_quote_widget_active), true));
        //TODO set up spinner values and store the current spinner value
        //        fontFamilySpinner.setSelection(0);
    }
}
