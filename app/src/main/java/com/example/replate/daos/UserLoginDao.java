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
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_JSON = "application/json";

    public static String createNewAccount(User user) {
        JSONObject jsonObject = userToJson(user);
        Map<String, String> header = new HashMap<>();
        header.put(CONTENT_TYPE, APPLICATION_JSON);
        return NetworkAdapter.httpRequest(BASE_URL, NetworkAdapter.POST, jsonObject, header);
    }

    public static String loginToAccount(String email, String password) {
        JSONObject jsonObject = userToJson(email, password);
        Map<String, String> header = new HashMap<>();
        header.put(CONTENT_TYPE, APPLICATION_JSON);
        return NetworkAdapter.httpRequest(BASE_URL + LOGIN_URL, NetworkAdapter.POST, jsonObject, header);
    }

    private static JSONObject userToJson(User user) { //converts user object to json
        String type = "company";
        if (user instanceof Volunteer) type = "volunteer";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("{\"type\":\"").append(type).append("\",")
                .append("\"name\":\"").append(user.getName()).append("\",")
                .append("\"phone\":\"").append(user.getPhoneNumber()).append("\",")
                .append("\"email\":\"").append(user.getEmailAddress()).append("\",")
                .append("\"password\":\"").append(user.getPassword()).append("\"}");
        try {
            return new JSONObject(stringBuilder.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static JSONObject userToJson(String email, String password) { //converts username and pw to json
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("{\"email\":\"").append(email).append("\",")
                .append("\"password\":\"").append(password).append("\"}");
        try {
            return new JSONObject(stringBuilder.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}

