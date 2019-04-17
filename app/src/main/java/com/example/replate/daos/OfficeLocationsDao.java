package com.example.replate.daos;

import com.example.replate.adapters.NetworkAdapter;
import com.example.replate.models.OfficeLocation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OfficeLocationsDao {

    private static final String LOCATION_URL = "https://replate-backend-turcan.herokuapp.com/api/locations/";


    public static String createNewLocation(OfficeLocation officeLocation, String token) {
        JSONObject jsonObject = locationToJson(officeLocation);
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        header.put("Authorization", token);
        return NetworkAdapter.httpRequest(LOCATION_URL, NetworkAdapter.POST, jsonObject, header);
    }

    public ArrayList<OfficeLocation>getBusinessLocationsList(String token) {
        String result = getAllLocations(token);
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

    private static String getAllLocations(String token){
        Map<String, String> header = new HashMap();
        header.put("Authorization", token);
        header.put("Content-Type", "application/json");
        String result = NetworkAdapter.httpRequest(LOCATION_URL, NetworkAdapter.GET, null, header);
        return result;
    }

    private static JSONObject locationToJson(OfficeLocation officeLocation) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("{\"office_name\":\"" + officeLocation.getOfficeName() + "\",")
                .append("\"office_address\":\"" + officeLocation.getOfficeAddress() + "\",")
                .append("\"office_email\":\"" + officeLocation.getOfficeEmail() + "\"");
        stringBuilder.append("}");

        try {
            return new JSONObject(stringBuilder.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
