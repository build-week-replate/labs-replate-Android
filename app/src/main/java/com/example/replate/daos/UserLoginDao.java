package com.example.replate.daos;

import com.example.replate.adapters.NetworkAdapter;
import com.example.replate.models.Business;
import com.example.replate.models.User;
import com.example.replate.models.Volunteer;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserLoginDao {
    private static final String BASE_URL = "https://replate-backend-turcan.herokuapp.com/api/users/";
    private static final String LOGIN_URL = "login";


    public static String createNewAccount(User user) {

        JSONObject jsonObject = toJson(user);
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        return NetworkAdapter.httpRequest(BASE_URL, NetworkAdapter.POST, jsonObject, header);
    }

    public static String loginToAccount(String email, String password) {

        JSONObject jsonObject = toJson(email, password);
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        return NetworkAdapter.httpRequest(BASE_URL + LOGIN_URL, NetworkAdapter.POST, jsonObject, header);
    }


    private static JSONObject toJson(User user) { //converts user object to json
        String type = "company";
        if (user instanceof Volunteer) type = "volunteer";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("{\"type\":\"" + type + "\",")
                .append("\"name\":\"" + user.getName() + "\",")
                .append("\"phone\":\"" + user.getPhoneNumber() + "\",")
                .append("\"email\":\"" + user.getEmailAddress() + "\",")
                .append("\"password\":\"" + user.getPassword() + "\"");
//                .append("\"address\":\"" + business.getAddress() + "\",")
//                .append("\"officeName\":\"" + business.getOfficeName());
//                if (business.getOfficeEmail()!=null)
//                    stringBuilder.append("\"," + "\"officeEmail\":" + business.getOfficeEmail());
        stringBuilder.append("}");

        try {
            return new JSONObject(stringBuilder.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject toJson(String email, String password) { //converts username and pw to json

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

