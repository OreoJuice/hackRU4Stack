package com.example.hackru;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.io.BufferedReader;

import java.io.FileReader;

public class QuotesFragment extends Fragment {

    private TextView quote;
    private Button button;

    private ArrayList<String> inspirationalQuotes;

    public QuotesFragment() {
        // Required empty public constructor
        super(R.layout.fragment_quotes);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quotes, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        //Choose Quote
        Quote newQuote = new Quote();
        String chosenQuote = newQuote.getChosenQuote();

        quote = (TextView)view.findViewById(R.id.quote);
        Log.d("quoteObject", quote.toString());
        quote.setText(chosenQuote);

        button = (Button)view.findViewById(R.id.buttonQuote);
        button.setOnClickListener(v -> {
            setText(new Quote().getChosenQuote());
        });
    }

    public void setText(String text){
        quote.setText(text);
        Log.d("quoteObject", quote.toString());
    }

}