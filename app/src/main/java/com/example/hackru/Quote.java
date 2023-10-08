package com.example.hackru;

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
    private List<String> quotes;
    private String chosenQuote;

    public Quote() {

//        quotes = getResource;
        quotes.add("Hi ao0");
        quotes.add("Hi Ba1");
        quotes.add("H Bao2");
        quotes.add("i Bao3");
        generateQuote();
    }

    public void generateQuote() {
        Random rand = new Random();
        int randomNum = rand.nextInt(quotes.size());
        chosenQuote = quotes.get(randomNum).toString();
    }
    public String getChosenQuote(){
        return chosenQuote;
    }

}
