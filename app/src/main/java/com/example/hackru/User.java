package com.example.hackru;

import java.io.Serializable;

public class User implements Serializable {
    private String firstName;
    private String lastName;
    private String town;

    public User(String firstName, String lastName, String town){
        this.firstName = firstName;
        this.lastName = lastName;
        this.town = town;
    }

    public void updateInfo(String type, String value){
        if(type.equals(firstName)){
            firstName = value;
        }
        if(type.equals(lastName)){
            lastName = value;
        }
        if(type.equals(town)){
            town = value;
        }
    }
}
