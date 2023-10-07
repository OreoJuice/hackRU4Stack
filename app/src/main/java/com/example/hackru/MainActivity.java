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


    private User user;
    public static final String USER = "user";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sp = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        String firstTime = sp.getString("FirstTimeOpen", "");

        if(firstTime.equals("Yes")){
            Intent intent = new Intent(this, NewUserActivity.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(this, HomeActivity.class);
            User myUser = deserializeSessionFromJson();
            Bundle bundle = new Bundle();
            bundle.putSerializable(USER, myUser);
            startActivity(intent);



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