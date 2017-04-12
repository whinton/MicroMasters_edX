package com.company;

import java.io.*;
import java.net.*;

public class InternetContent {

    public static String get(String url) throws Exception {

        URL page = new URL(url);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(page.openStream()));

        String inputLine;
        StringBuilder data = new StringBuilder();

        while ((inputLine = in.readLine()) != null)
            //System.out.println(inputLine);
            data.append(inputLine);
        in.close();
        return data.toString();
    }
}
