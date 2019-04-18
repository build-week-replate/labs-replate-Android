package com.example.replate.models;

import java.io.Serializable;

public class Volunteer extends User implements Serializable {

    public Volunteer(String name, int phoneNumber, String emailAddress, String password) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.password = password;
    }
}
