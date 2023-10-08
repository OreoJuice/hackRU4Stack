package com.example.hackru;

import java.util.ArrayList;

public class Weather {

    private double temperature;
    private String condition;
    private String iconURL;
    private double windSpeed;
    private ArrayList<String> recommendation;

    public Weather(double temperature, String condition, String iconURL, double windSpeed){
        this.temperature = temperature;
        this.condition = condition;
        this.iconURL = iconURL;
        this.windSpeed = windSpeed;
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
}