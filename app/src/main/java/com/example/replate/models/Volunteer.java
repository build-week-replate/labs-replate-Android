package com.example.replate.models;

import java.util.ArrayList;

public class Volunteer extends User{
    private ArrayList<PickupRequest> assignedPickups;

    public Volunteer(String name, int phoneNumber, String emailAddress, String password) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.password = password;
    }
}
