package org.example;

import java.util.Scanner;

public class HttpImageStatusCli {
    private final HttpStatusImageDownloader downloader = new HttpStatusImageDownloader();

    public void askStatus() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter HTTP status code: ");
        String input = scanner.nextLine();

        try {
            int code = Integer.parseInt(input);
            downloader.downloadStatusImage(code);
        } catch (NumberFormatException e) {
            System.out.println("Please enter valid number");
        } catch (Exception e) {
            System.out.println("There is no image for HTTP status " + input);
        }
    }

    public static void main(String[] args) {
        HttpImageStatusCli cli = new HttpImageStatusCli();
        cli.askStatus();
    }
}
