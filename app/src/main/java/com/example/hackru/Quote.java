package com.example.hackru;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import com.google.gson.Gson;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class Quote {
    private String[] quotes;
    private String chosenQuote;

    private Context context;

    public Quote(Context current) {
        this.context = current;
        quotes = current.getResources().getStringArray(R.array.quotes);
        Random rand = new Random();
        chosenQuote = quotes[(rand.nextInt(quotes.length))];
    }

    public String getChosenQuote(){
        return chosenQuote;
    }

}
