package com.example.hackru;

import android.util.Log;

import java.util.ArrayList;

public class Weather {

    private double temperature;
    private double temp_min;
    private double temp_max;
    private String condition;
    private String iconURL;
    private String location;
    private ArrayList<String> recommendation;

    public Weather(double temperature, String condition, String iconURL, double temp_min, double temp_max){
        this.temperature = temperature;
        this.condition = condition;
        this.iconURL = iconURL;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
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
    public double getMin() {return temp_min; }
    public double getMax() {return temp_max; }
    public String getLocation(){return location; }
}