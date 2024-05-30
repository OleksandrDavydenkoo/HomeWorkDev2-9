package org.example;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpStatusImageDownloader {
    private final HttpStatusChecker checker = new HttpStatusChecker();

    public void downloadStatusImage(int code) throws Exception {
        String imageUrl = checker.getStatusImage(code);
        URL url = new URL(imageUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (InputStream inputStream = connection.getInputStream();
                 FileOutputStream outputStream = new FileOutputStream(code + ".jpg")) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                System.out.println("Downloaded image for status code: " + code);
            }
        } else {
            throw new Exception("Failed to download image for status code: " + code);
        }
    }

    public static void main(String[] args) {
        HttpStatusImageDownloader downloader = new HttpStatusImageDownloader();
        try {
            downloader.downloadStatusImage(200);
            downloader.downloadStatusImage(10000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
