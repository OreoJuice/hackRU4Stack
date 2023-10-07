package com.example.hackru;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;


public class MainActivity extends AppCompatActivity {

    private ListView userView;
    private TextView textView;
    public ArrayAdapter<User> adapterUser;
    public ArrayAdapter<Weather> adapterWeather;
    public ArrayAdapter<Quote> adapterQuote;
    public static final String USER = "album";
    public static final String INDEX = "index";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User myUser = deserializeSessionFromJson();
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
        User myClass = gson.fromJson(jUser, User.class);
        return myClass;
    }
}