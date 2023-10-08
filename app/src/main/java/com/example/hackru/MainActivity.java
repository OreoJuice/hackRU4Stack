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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Check if first boot
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(getString(R.string.shared_prefs), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        deleteAllSharedPrefs(sharedPref, editor);
        if(sharedPref.getString(getString(R.string.already_booted),"").isEmpty()) {
           //First Time, set prefs, boot up settings w/ dialog
            //TODO define default values for sharedpreferences
            editor.putString(getString(R.string.already_booted), "true");
            editor.putBoolean(getString(R.string.weather_widget_active), true);
            editor.putBoolean(getString(R.string.celsius), true);
            editor.putBoolean(getString(R.string.motivational_quote_widget_active), true);
            //TODO fix default font
            editor.putString(getString(R.string.font_family), "kanit");
            editor.apply();

            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
    }

    public void deleteAllSharedPrefs(SharedPreferences sharedPref, SharedPreferences.Editor editor) {
        editor.remove(getString(R.string.already_booted));
        editor.remove(getString(R.string.user_name));
        editor.remove(getString(R.string.weather_widget_active));
        editor.remove(getString(R.string.celsius));
        editor.remove(getString(R.string.motivational_quote_widget_active));
        editor.remove(getString(R.string.font_family));
    }

//    public String serializeUser(User myUser){
//        SharedPreferences sp = getPreferences(MODE_PRIVATE);
//        SharedPreferences.Editor pe = sp.edit();
//        Gson gson = new Gson();
//        String jUser = gson.toJson(myUser);
//        pe.putString("MyUser", jUser);
//        pe.apply();
//        return jUser;
//    }
//
//    public User deserializeSessionFromJson() {
//        Gson gson = new Gson();
//        SharedPreferences sp = getPreferences(MODE_PRIVATE);
//        String jUser = sp.getString("MyUser", null);
//        return gson.fromJson(jUser, User.class);
//    }
}