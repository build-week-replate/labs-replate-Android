package com.example.replate.models;

import org.json.JSONObject;

import retrofit2.Retrofit;

public class Business {
    private String companyName;
    private int phoneNumber;
    private String emailAddress;
    private String address;
    private String officeName;
    private String officeEmail;
    private String password;

    public Business(String companyName, int phoneNumber, String emailAddress, String password, String address, String officeName, String officeEmail) {
        this.officeEmail = officeEmail;
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.address = address;
        this.officeName = officeName;
        this.password = password;
    }

    public Business(String companyName, int phoneNumber, String emailAddress, String password, String address, String officeName) {
        this(companyName, phoneNumber, emailAddress, password, address, officeName, null);
    }

    public String getCompanyName() {
        return companyName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getAddress() {
        return address;
    }

    public String getOfficeName() {
        return officeName;
    }

    public String getOfficeEmail() {
        return officeEmail;
    }

    public String getPassword() {
        return password;
    }
}
