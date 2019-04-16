package com.example.replate.models;

import java.io.Serializable;

public class User implements Serializable {
    String name;
    String emailAddress;
    int phoneNumber;
    String password;


    public String getName() {
        return name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }
}
