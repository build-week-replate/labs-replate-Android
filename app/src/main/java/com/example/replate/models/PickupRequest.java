package com.example.replate.models;

import org.json.JSONException;
import org.json.JSONObject;

public class PickupRequest {
    private String name;
    private String time;
    private String date;
    private String instructions;
    private String notes; //optional

    public PickupRequest(String name, String time, String date, String instructions, String notes) {
        this.name = name;
        this.time = time;
        this.date = date;
        this.instructions = instructions;
        this.notes = notes;
    }

    public PickupRequest(JSONObject temp) {
        try {
            this.name = temp.getString("pickup_name");
            this.time = temp.getString("pickup_time");
            this.date = temp.getString("pickup_date");
            this.instructions = temp.getString("pickup_comment");
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
}
