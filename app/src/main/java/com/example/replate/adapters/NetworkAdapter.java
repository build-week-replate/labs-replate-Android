package com.example.replate.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class NetworkAdapter {
    public static final String GET     = "GET";
    public static final String POST    = "POST";
    public static final String HEAD    = "HEAD";
    public static final String OPTIONS = "OPTIONS";
    public static final String PUT     = "PUT";
    public static final String DELETE  = "DELETE";
    public static final String TRACE   = "TRACE";

    static String httpRequest(String urlString) {
        return httpRequest(urlString, GET, null, null);
    }

    public static String httpRequest(String urlString, String requestMethod) {
        return httpRequest(urlString, requestMethod, null, null);
    }

    public static String httpRequest(String urlString, String requestMethod, JSONObject requestBody, Map<String, String> headerProperties) {
        String             result      = "";
        InputStream        inputStream = null;
        HttpsURLConnection connection  = null;

        try {
            URL url = new URL(urlString);
            connection = (HttpsURLConnection) url.openConnection();

            connection.setRequestMethod(requestMethod);

            if (headerProperties != null) {
                for (Map.Entry<String, String> property : headerProperties.entrySet()) {
                    connection.setRequestProperty(property.getKey(), property.getValue());
                }
            }
            if((requestMethod.equals(POST) || requestMethod.equals(PUT)) && requestBody != null) {
                connection.setDoInput(true);
                final OutputStream outputStream = connection.getOutputStream();
                outputStream.write(requestBody.toString().getBytes());
                outputStream.close();
            } else {
                connection.connect();
            }

            final int responseCode = connection.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK ) {
                inputStream = connection.getInputStream();
                if (inputStream != null) {
                    BufferedReader reader  = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder  builder = new StringBuilder();

                    String line;
                    do {
                        line = reader.readLine();
                        builder.append(line);
                    } while (line != null);
                    result = builder.toString();
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                connection.disconnect();
            }
        }
        return result;
    }
}