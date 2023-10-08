package com.example.hackru;

import android.util.Log;

import java.util.ArrayList;

public class Weather {

    private double temperature;
    private String condition;
    private String iconURL;
    private ArrayList<String> recommendation;

    public Weather(double temperature, String condition, String iconURL){
        this.temperature = temperature;
        this.condition = condition;
        this.iconURL = iconURL;
        this.recommendation = recommendItems(temperature, condition);
    }

    public ArrayList<String> recommendItems(double temperature, String condition){
        recommendation = new ArrayList<>();
        if(condition.equals("Rain")){
            recommendation.add("Wear a jacket, or bring an umbrella.");
        }
        if(condition.equals("Clear")){
            if(temperature > 75.0){
                recommendation.add("Dress lightly for hot weather.");
            }
            else if(temperature < 55.0 ){
                recommendation.add("Bundle up, it's cold outside!");
            }
        }
        return recommendation;
    }

    public String getIconURL() {
        return iconURL;
    }
    public double getTemperature() {return temperature; }
}