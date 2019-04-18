package com.example.replate.daos;

import com.example.replate.adapters.NetworkAdapter;
import com.example.replate.models.PickupRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PickupRequestsDao {

    private static final String PICKUPS_URL = "https://replate-backend-turcan.herokuapp.com/api/schedules/";
    private static final String TAKE_URL = "/take";




    public static String getAllPickups(String token){
        Map<String, String> header = new HashMap();
        header.put("Authorization", token);
        header.put("Content-Type", "application/json");
        String result = NetworkAdapter.httpRequest(PICKUPS_URL, NetworkAdapter.GET, null, header);
        return result;
    }

    public static String postPickupRequest(PickupRequest pickupRequest, String token){
        JSONObject jsonObject = toJson(pickupRequest);
        Map<String, String> header = new HashMap();
        header.put("Authorization", token);
        header.put("Content-Type", "application/json");
        String result = NetworkAdapter.httpRequest(PICKUPS_URL, NetworkAdapter.POST, jsonObject, header);
        return result;
    }


    private static JSONObject toJson(PickupRequest pickupRequest) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("pickup_name", pickupRequest.getName());
            jsonObject.put("pickup_date", pickupRequest.getDate());
            jsonObject.put("pickup_time", pickupRequest.getTime());
            jsonObject.put("pickup_comment", pickupRequest.getInstructions());
            jsonObject.put("pickup_additional_comment", pickupRequest.getNotes());
            jsonObject.put("location_id", pickupRequest.getLocationId());
            return jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String editPickupRequest(PickupRequest pickupRequest, String token){
        JSONObject jsonObject = toJson(pickupRequest);
        Map<String, String> header = new HashMap();
        header.put("Authorization", token);
        header.put("Content-Type", "application/json");
        String result = NetworkAdapter.httpRequest(PICKUPS_URL + pickupRequest.getId(), NetworkAdapter.PATCH, jsonObject, header);
        return result;
    }

    public static String assignPickup(PickupRequest pickupRequest, String token) {
        JSONObject jsonObject = toJson(pickupRequest);
        Map<String, String> header = new HashMap();
        header.put("Authorization", token);
        header.put("Content-Type", "application/json");
        String result = NetworkAdapter.httpRequest(PICKUPS_URL + String.valueOf(pickupRequest.getId()) + TAKE_URL, NetworkAdapter.PATCH, jsonObject, header);
        return result;
    }

    public static String deletePickup(PickupRequest pickupRequest, String token) {
        JSONObject jsonObject = toJson(pickupRequest);
        Map<String, String> header = new HashMap();
        header.put("Authorization", token);
        header.put("Content-Type", "application/json");
        String result = NetworkAdapter.httpRequest(PICKUPS_URL + String.valueOf(pickupRequest.getId()), NetworkAdapter.DELETE, jsonObject, header);
        return result;
    }

}
