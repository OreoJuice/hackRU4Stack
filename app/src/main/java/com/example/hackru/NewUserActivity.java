package com.example.hackru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.gson.Gson;

public class NewUserActivity extends AppCompatActivity {

    public static final String USER = "album";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
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
}