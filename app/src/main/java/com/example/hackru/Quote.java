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

    public Quote(){
        Random rand = new Random(1449);
        quotes = new ArrayList<>();
        try{
            JsonReader reader = new JsonReader(new FileReader("./app/res/raw/quotes.json"));

            quotes = Arrays.asList(new Gson().fromJson(reader, String[].class));
            reader.close();

        }catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        int max = quotes.size()-1;
        int randomNum = rand.nextInt((max - 1) + 1) + 1;
        chosenQuote = quotes.get(randomNum).toString();

    }

    public String getChosenQuote(){
        return chosenQuote;
    }

}
