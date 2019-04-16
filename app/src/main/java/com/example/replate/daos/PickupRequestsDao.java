package com.example.replate.daos;

import com.example.replate.adapters.NetworkAdapter;
import com.example.replate.models.PickupRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PickupRequestsDao {

    private static final String PICKUPS_URL = "https://replate-backend-turcan.herokuapp.com/api/schedules";



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
            return jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
