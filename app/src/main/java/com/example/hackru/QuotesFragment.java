package com.example.hackru;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuotesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuotesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ImageView imageView;
    private TextView quotes;
    private ArrayList<String> inspirationalQuotes;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public QuotesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuotesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuotesFragment newInstance(String param1, String param2) {
        QuotesFragment fragment = new QuotesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Quote newQuote = new Quote();
        String chosenQuote = newQuote.getChosenQuote();
        View view = inflater.inflate(R.layout.fragment_quotes, container, false);
        TextView quote = (TextView)view.findViewById(R.id.quote);
        quote.setText(chosenQuote);
        Button button = (Button) view.findViewById(R.id.buttonQuote);
        super.onViewCreated(view, savedInstanceState);
        button.setOnClickListener(v -> {
            // do something
            quote.setText(chosenQuote);
        });
        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        View view = getView();

        if(view!=null){
            Quote newQuote = new Quote();
            String chosenQuote = "fuck";
            TextView quote = (TextView)view.findViewById(R.id.quote);
            quote.setText(chosenQuote);
        }
    }

}