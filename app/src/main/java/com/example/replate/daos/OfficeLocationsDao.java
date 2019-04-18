package com.example.replate.daos;

import com.example.replate.adapters.NetworkAdapter;
import com.example.replate.models.OfficeLocation;
import com.example.replate.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OfficeLocationsDao {

    private static final String LOCATION_URL = "https://replate-backend-turcan.herokuapp.com/api/locations/";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_JSON = "application/json";
    private static final String AUTHORIZATION = "Authorization";


    public static void createNewLocation(OfficeLocation officeLocation, String token) {
        JSONObject jsonObject = locationToJson(officeLocation);
        Map<String, String> header = new HashMap<>();
        header.put(CONTENT_TYPE, APPLICATION_JSON);
        header.put(AUTHORIZATION, token);
        NetworkAdapter.httpRequest(LOCATION_URL, NetworkAdapter.POST, jsonObject, header);
    }

    public static ArrayList<OfficeLocation> getBusinessLocationsList(User user) {
        String result = getAllLocations(user);
        ArrayList<OfficeLocation> locations = new ArrayList<>();
        try {
            JSONArray jsonArrayResult = new JSONArray(result);
            for (int i = 0; i < jsonArrayResult.length(); ++i) {
                JSONObject jsonObject = jsonArrayResult.getJSONObject(i);
                locations.add(new OfficeLocation(jsonObject));
            }
            return locations;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getAllLocations(User user){
        HashMap<String, String> header = new HashMap<>();
        header.put(CONTENT_TYPE, APPLICATION_JSON);
        header.put(AUTHORIZATION, user.getToken());
        return NetworkAdapter.httpRequest(LOCATION_URL + user.getId(), NetworkAdapter.GET, null, header);
    }

    private static JSONObject locationToJson(OfficeLocation officeLocation) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("{\"office_name\":\"").append(officeLocation.getOfficeName()).append("\",")
                .append("\"office_address\":\"").append(officeLocation.getOfficeAddress()).append("\",")
                .append("\"office_email\":\"").append(officeLocation.getOfficeEmail()).append("\"}");

        try {
            return new JSONObject(stringBuilder.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
