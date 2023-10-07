package com.example.hackru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private ListView userView;
    private TextView textView;
    public ArrayAdapter<User> adapterUser;
    public ArrayAdapter<Weather> adapterWeather;
    public ArrayAdapter<Quote> adapterQuote;
    private User user;
    public static final String USER = "user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        //Deserializes user info
        user = (User)extras.getSerializable(USER);

    }
}