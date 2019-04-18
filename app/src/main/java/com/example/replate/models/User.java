package com.example.replate.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class User implements Serializable {
    String name;
    String emailAddress;
    String phoneNumber;
    String password;
    private String token;
    private String type;
    private int id;


    public User(JSONObject jsonObject){
        try {
            JSONObject temp = jsonObject.getJSONObject("user");
            name = temp.getString("name");
            emailAddress = temp.getString("email");
            phoneNumber = temp.getString("phone");
            type = temp.getString("type");
            id = temp.getInt("id");
            token = jsonObject.getString("token");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    User() {
    }

    public String getToken() { return token; }

    public String getType() { return type; }

    public String getName() {
        return name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public int getId() { return id; }
}
