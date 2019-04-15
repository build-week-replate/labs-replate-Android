package com.example.replate.models;

import org.json.JSONObject;

import retrofit2.Retrofit;

public class Business {
    private String companyName;
    private String phoneNumber;
    private String emailAddress;
    private String address;
    private String officeName;
    private String officeEmail;

    public Business(String companyName, String phoneNumber, String emailAddress, String address, String officeName, String officeEmail) {
        this.officeEmail = officeEmail;
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.address = address;
        this.officeName = officeName;
    }

    public Business(String companyName, String phoneNumber, String emailAddress, String address, String officeName) {
        this(companyName, phoneNumber, emailAddress, address, officeName, null);
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getPhoneNumber() {
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
}
