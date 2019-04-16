package com.example.replate.models;

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
