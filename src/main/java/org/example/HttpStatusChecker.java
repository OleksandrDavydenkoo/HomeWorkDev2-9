package org.example;

import java.net.HttpURLConnection;
import java.net.URL;

public class HttpStatusChecker {
    private static final String BASE_URL = "https://http.cat/";

    public String getStatusImage(int code) throws Exception {
        String urlString = BASE_URL + code + ".jpg";
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            return urlString;
        } else {
            throw new Exception("Image not found for status code: " + code);
        }
    }

    public static void main(String[] args) {
        HttpStatusChecker checker = new HttpStatusChecker();
        try {
            System.out.println(checker.getStatusImage(200));
            System.out.println(checker.getStatusImage(10000));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}