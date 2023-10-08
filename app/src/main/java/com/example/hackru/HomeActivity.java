package com.example.hackru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    private Button settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        settings = (Button) findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSettings();
            }
        });

        public void openSettings() {
            Intent intentSettings = new Intent(this, settingsActivity.class);
            startActivity(intentSettings);
        }


        //Deserializes user info
        user = (User)extras.getSerializable(USER);
        //Gets user location and spawns in a new weather object

    }
}