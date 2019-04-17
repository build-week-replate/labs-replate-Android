package com.example.replate.models;

import android.support.annotation.Nullable;

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

    public PickupRequest(String name, String time, String date, String instructions, String notes, int id) {
        this.name = name;
        this.time = time;
        this.date = date;
        this.instructions = instructions;
        this.notes = notes;
        this.id = id;
    }
    public PickupRequest(String name, String time, String date, String instructions, String notes) {
        this(name, time, date, instructions, notes, 0);
    }

    public PickupRequest(JSONObject temp) {
        try {
            this.name = temp.getString("pickup_name");
            this.time = temp.getString("pickup_time");
            this.date = temp.getString("pickup_date");
            this.instructions = temp.getString("pickup_comment");
            this.company_id = temp.getInt("company_id");
            this.id = temp.getInt("id");
            try {
                this.volunteer_id = temp.getInt("volunteer_id");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.taken = temp.getBoolean("taken");
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
}
