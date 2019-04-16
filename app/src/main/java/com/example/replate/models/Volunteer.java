package com.example.replate.models;

import android.os.Parcelable;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class Volunteer extends User implements Serializable {
    private ArrayList<PickupRequest> assignedPickups;

    public Volunteer(String name, int phoneNumber, String emailAddress, String password) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.password = password;
    }
}
