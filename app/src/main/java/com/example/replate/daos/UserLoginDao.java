package com.example.replate.daos;

import com.example.replate.adapters.NetworkAdapter;
import com.example.replate.models.OfficeLocation;
import com.example.replate.models.User;
import com.example.replate.models.Volunteer;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserLoginDao {
    private static final String BASE_URL = "https://replate-backend-turcan.herokuapp.com/api/users/";
    private static final String LOGIN_URL = "login";
    private static final String LOCATION_URL = "https://replate-backend-turcan.herokuapp.com/api/locations/";


    public static String createNewAccount(User user) {

        JSONObject jsonObject = userToJson(user);
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        return NetworkAdapter.httpRequest(BASE_URL, NetworkAdapter.POST, jsonObject, header);
    }

    public static String createNewLocation(User user, OfficeLocation officeLocation) {
        JSONObject jsonObject = locationToJson(officeLocation);
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        header.put("Authorization", user.getToken());
        return NetworkAdapter.httpRequest(LOCATION_URL, NetworkAdapter.POST, jsonObject, header);
    }

    public static String loginToAccount(String email, String password) {

        JSONObject jsonObject = userToJson(email, password);
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        return NetworkAdapter.httpRequest(BASE_URL + LOGIN_URL, NetworkAdapter.POST, jsonObject, header);
    }


    private static JSONObject locationToJson(OfficeLocation officeLocation) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("\"office_name\":\"" + officeLocation.getOfficeName() + "\",")
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

    private static JSONObject userToJson(User user) { //converts user object to json
        String type = "company";
        if (user instanceof Volunteer) type = "volunteer";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("{\"type\":\"" + type + "\",")
                .append("\"name\":\"" + user.getName() + "\",")
                .append("\"phone\":\"" + user.getPhoneNumber() + "\",")
                .append("\"email\":\"" + user.getEmailAddress() + "\",")
                .append("\"password\":\"" + user.getPassword() + "\"");
        stringBuilder.append("}");

        try {
            return new JSONObject(stringBuilder.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject userToJson(String email, String password) { //converts username and pw to json

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("{\"email\":\"" + email + "\",")
                .append("\"password\":\"" + password + "\"")
                .append("}");
        try {
            return new JSONObject(stringBuilder.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}

