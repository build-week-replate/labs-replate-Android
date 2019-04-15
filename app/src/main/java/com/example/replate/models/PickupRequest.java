package com.example.replate.models;

public class PickupRequest {
    private Business business;
    private String timeToPickup;
    private String typeOfFood;
    private long foodExpiration;
    private long timePosted;
    private Volunteer volunteer = null;



    public Volunteer getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }
}
