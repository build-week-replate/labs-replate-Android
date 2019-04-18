package com.example.replate.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class PickupRequest implements Serializable {
    private String name;
    private String time;
    private String date;
    private String instructions;
    private String notes; //optional
    private boolean taken;
    private int volunteer_id; //null if not taken
    private int company_id;
    private int id;
    private int locationId;
    private String officeaddress;
    private String officeName;
    private String officeEmail; //optional

    public PickupRequest(String name, String time, String date, String instructions, String notes, int id, int locationId) {
        this.name = name;
        this.time = time;
        this.date = date;
        this.instructions = instructions;
        this.notes = notes;
        this.id = id;
        this.locationId = locationId;
    }
    public PickupRequest(String name, String time, String date, String instructions, String notes, int locationId) {
        this(name, time, date, instructions, notes, 0, locationId);
    }

    public PickupRequest(JSONObject temp) {
        try {
            this.name = temp.getString("pickup_name");
            this.time = temp.getString("pickup_time");
            this.date = temp.getString("pickup_date");
            this.instructions = temp.getString("pickup_comment");
            this.company_id = temp.getInt("company_id");
            this.id = temp.getInt("id");
            this.taken = temp.getBoolean("taken");
            this.locationId = temp.getInt("location_id");
            this.officeName = temp.getString("office_name");
            this.officeaddress = temp.getString("office_address");

            //these fields are optional/may be null
            try {
                this.officeEmail = temp.getString("office_email");
            }catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                this.volunteer_id = temp.getInt("volunteer_id");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                this.notes = temp.getString("pickup_additional_comment");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getNotes() {
        return notes;
    }

    public boolean isTaken() {
        return taken;
    }

    public int getVolunteer_id() {
        return volunteer_id;
    }

    public int getCompany_id() {
        return company_id;
    }

    public int getId() {
        return id;
    }

    public String getOfficeAddress() { return officeaddress; }

    public String getOfficeName() { return officeName; }

    public String getOfficeEmail() { return officeEmail; }

    public int getLocationId() { return locationId; }

    public void setId(int id) { this.id = id; }
}
