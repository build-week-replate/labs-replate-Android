package com.example.replate.daos;

import com.example.replate.adapters.NetworkAdapter;
import com.example.replate.models.Business;
import com.example.replate.models.Volunteer;

import org.json.JSONException;
import org.json.JSONObject;

public class UserLoginDao {
    private static final String BASE_URL = "https://replate-backend-turcan.herokuapp.com/api/users/";

    private static final String END_URL = "";
    private static final String LOGIN_URL = "login";


    public static String createNewAccount(Business business) {

        JSONObject jsonObject = toJson(business);
        return NetworkAdapter.httpRequest(BASE_URL + END_URL, NetworkAdapter.POST, jsonObject, null);
    }

    public static String loginToAccount(String email, String password){

        JSONObject jsonObject = toJson(email, password);
        return NetworkAdapter.httpRequest(BASE_URL + LOGIN_URL + END_URL, NetworkAdapter.POST, jsonObject, null );
    }


    public static JSONObject toJson(Business business) { //converts business object to json

        /*{
            "type": "string", // can be either company or volunteer
                "name": "string", // is unique
                "phone": integer,
                "email": "string", // is unique
                "password": "string"
        }*/
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("{\"type\":\"company\",")
                .append("\"name\":\"" + business.getCompanyName() + "\",")
                .append("\"phone\":\"" + business.getPhoneNumber() + "\",")
                .append("\"email\":\"" + business.getEmailAddress() + "\",")
                .append("\"password\":\"" + business.getPassword() + "\"");
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

//        {
//            "email": “string",
//            "password": “string"
//        }
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



   /* public static JSONObject toJson(Volunteer volunteer) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("{\"businessName\":\"" + business.getCompanyName() + "\",")
                .append("\"address\":\"" + business.getAddress() + "\",")
                .append("\"email\":\"" + business.getEmailAddress() + "\",")
                .append("\"phone\":\"" + business.getOfficeName() + "\",")
                .append("\"officeName\":\"" + business.getOfficeName());
        if (business.getOfficeEmail() != null)
            stringBuilder.append("\"," + "\"officeEmail\":" + business.getOfficeEmail());
        stringBuilder.append("}");

        try {
            return new JSONObject(stringBuilder.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }*/
}
