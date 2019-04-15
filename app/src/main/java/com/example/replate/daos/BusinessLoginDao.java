package com.example.replate.daos;

import com.example.replate.adapters.NetworkAdapter;
import com.example.replate.models.Business;

import org.json.JSONException;
import org.json.JSONObject;

public class BusinessLoginDao {
    private static final String BASE_URL = "https://replate-91aee.firebaseio.com/";
    private static final String END_URL = ".json";




    public static void createNewAccount(Business business) {

        JSONObject jsonObject = toJson(business);
        NetworkAdapter.httpRequest(BASE_URL + END_URL, NetworkAdapter.POST, jsonObject, null);
    }


    public static JSONObject toJson(Business business) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("{\"businessName\":\"" + business.getCompanyName() + "\",")
                .append("\"address\":\"" + business.getAddress() + "\",")
                .append("\"email\":\"" + business.getEmailAddress() + "\",")
                .append("\"phone\":\"" + business.getOfficeName() + "\",")
                .append("\"officeName\":\"" + business.getOfficeName());
                if (business.getOfficeEmail()!=null)
                    stringBuilder.append("\"," + "\"officeEmail\":" + business.getOfficeEmail());
                    stringBuilder.append("}");

        try {
            return new JSONObject(stringBuilder.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
