package com.example.replate.models;

import org.json.JSONException;
import org.json.JSONObject;

public class OfficeLocation {

    private String officeName;
    private String officeAddress;
    private String officeEmail;
    private int id;
    private int businessId;

    public OfficeLocation(String officeName, String officeAddress,  String officeEmail) {
        this.officeAddress = officeAddress;
        this.officeName = officeName;
        this.officeEmail = officeEmail;
    }

    public OfficeLocation(String officeName, String officeAddress,  String officeEmail, int id, int businessId) {
        this(officeName, officeAddress, officeEmail);
        this.id = id;
        this.businessId = businessId;
    }

    public OfficeLocation(JSONObject jsonObject) {
        try {
            this.officeName = jsonObject.getString("office_name");
            this.officeAddress = jsonObject.getString("office_address");
            this.officeEmail = jsonObject.getString("office_email");
            this.id = jsonObject.getInt("id");
            this.businessId = jsonObject.getInt("company_id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
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

    public int getId() { return id; }

    public int getBusinessId() { return businessId; }
}
