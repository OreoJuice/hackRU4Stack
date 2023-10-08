package com.example.hackru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;


public class MainActivity extends AppCompatActivity {

    protected FrameLayout content;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedPreferencesEditor;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

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

        //Check if first boot
        sharedPreferences = getApplicationContext().getSharedPreferences(getString(R.string.shared_prefs), Context.MODE_PRIVATE);
        sharedPreferencesEditor= sharedPreferences.edit();

        deleteAllSharedPrefs();
        if(sharedPreferences.getString(getString(R.string.already_booted),"").isEmpty()) {
            //First Time, set prefs, boot up settings w/ dialog
            sharedPreferencesEditor.putString(getString(R.string.already_booted), "true");
            sharedPreferencesEditor.putBoolean(getString(R.string.weather_widget_active), true);
            sharedPreferencesEditor.putBoolean(getString(R.string.celsius), true);
            sharedPreferencesEditor.putBoolean(getString(R.string.motivational_quote_widget_active), true);
            sharedPreferencesEditor.putString(getString(R.string.font_family), "kanit");
            sharedPreferencesEditor.apply();

            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }


    }

    public void deleteAllSharedPrefs() {
        sharedPreferencesEditor.remove(getString(R.string.already_booted));
        sharedPreferencesEditor.remove(getString(R.string.user_name));
        sharedPreferencesEditor.remove(getString(R.string.weather_widget_active));
        sharedPreferencesEditor.remove(getString(R.string.celsius));
        sharedPreferencesEditor.remove(getString(R.string.motivational_quote_widget_active));
        sharedPreferencesEditor.remove(getString(R.string.font_family));
    }
}