package com.example.replate.models;

public class Business extends User{
    private String address;
    private String officeName;
    private String officeEmail;

    public Business(String name, int phoneNumber, String emailAddress, String password, String address, String officeName, String officeEmail) {
        this.officeEmail = officeEmail;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.address = address;
        this.officeName = officeName;
        this.password = password;
    }

    public Business(String name, int phoneNumber, String emailAddress, String password, String address, String officeName) {
        this(name, phoneNumber, emailAddress, password, address, officeName, null);
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
