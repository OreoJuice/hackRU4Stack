package com.example.hackru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Button;
import android.view.View;
import android.content.Intent;

import static com.google.android.material.internal.ContextUtils.getActivity;

public class SettingsActivity extends AppCompatActivity{

    private EditText nameEditText;
    private Switch weatherWidgetSwitch;
    private Switch celsiusSwitch;
    private Switch motivationalQuotesWidget;
    private Spinner fontFamilySpinner;

    private SharedPreferences sharedPref;
    private SharedPreferences.Editor sharedPrefEditor;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        sharedPref = getApplicationContext().getSharedPreferences(getString(R.string.shared_prefs), Context.MODE_PRIVATE);
        sharedPrefEditor = sharedPref.edit();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.weatherFragmentContainerView, WeatherFragment.class, null)
                    .commit();
        }

        instantiateViewObjects();
//        Log.d("123SettingsActivity", sharedPref.getString(getString(R.string.user_name),""));

        button = (Button) findViewById(R.id.returnToMenu);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onBackPressed();
            }
        });
    }

    private void instantiateViewObjects() {
        //Name
        nameEditText = (EditText)findViewById(R.id.nameEditText);
        nameEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                sharedPrefEditor.putString(getString(R.string.user_name),nameEditText.getText().toString());
                sharedPrefEditor.apply();
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
        nameEditText.setText(sharedPref.getString(getString(R.string.user_name),""));

        //Weather Widget
        weatherWidgetSwitch = (Switch)findViewById(R.id.weatherWidgetSwitch);
        weatherWidgetSwitch.setChecked(sharedPref.getBoolean(getString(R.string.weather_widget_active), true));
        weatherWidgetSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sharedPrefEditor.putBoolean(getString(R.string.weather_widget_active), isChecked);
                sharedPrefEditor.apply();
            }
        });

        celsiusSwitch = (Switch)findViewById(R.id.celsiusSwitch);
        celsiusSwitch.setChecked(sharedPref.getBoolean(getString(R.string.celsius), true));
        celsiusSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sharedPrefEditor.putBoolean(getString(R.string.celsius), isChecked);
                sharedPrefEditor.apply();
            }
        });

        //Motivational Quotes Widget
        motivationalQuotesWidget = (Switch)findViewById(R.id.motivationalQuotesWidget);
        motivationalQuotesWidget.setChecked(sharedPref.getBoolean(getString(R.string.motivational_quote_widget_active), true));
        motivationalQuotesWidget.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sharedPrefEditor.putBoolean(getString(R.string.motivational_quote_widget_active), isChecked);
                sharedPrefEditor.apply();
            }
        });

        String[] values = getResources().getStringArray(R.array.font_family_choices);
        fontFamilySpinner = (Spinner)findViewById(R.id.fontFamilySpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fontFamilySpinner.setAdapter(adapter);
        //TODO set up spinner values and store the current spinner value
        //        fontFamilySpinner.setSelection(0);
    }
}
