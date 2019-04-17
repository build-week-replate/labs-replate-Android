package com.example.replate.models;

public class OfficeLocation {

    private String officeAddress;
    private String officeName;
    private String officeEmail;

    public OfficeLocation(String officeName, String officeAddress,  String officeEmail) {
        this.officeAddress = officeAddress;
        this.officeName = officeName;
        this.officeEmail = officeEmail;
    }



    public String getOfficeAddress() {
        return officeAddress;
    }

    public String getOfficeName() {
        return officeName;
    }

    public String getOfficeEmail() {
        return officeEmail;
    }

}
