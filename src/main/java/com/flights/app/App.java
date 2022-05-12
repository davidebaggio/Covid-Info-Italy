package com.flights.app;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import kong.unirest.*;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

public class App {
    public static void main(String[] args) throws IOException {
        System.out.println("\n");

        HttpResponse<String> response = Unirest.get("https://covid-19-statistics.p.rapidapi.com/reports?iso=ITA")
                .header("X-RapidAPI-Host", "covid-19-statistics.p.rapidapi.com")
                .header("X-RapidAPI-Key", "9b0d2a93ffmsh8b2a384a1b018c2p1903c8jsnc19568bae2e6").asString();

        System.out.println(response.getStatus() + "\n");

        JSONObject j = new JSONObject(response.getBody());
        JSONArray array = j.getJSONArray("data");

        System.out.println("Covid info in Italy per region: ");

        FileWriter f = new FileWriter("covid_info.txt");

        for (int i = 0; i < array.length(); i++) {
            JSONObject data = array.getJSONObject(i);
            JSONObject r = data.getJSONObject("region");
            String last_update = data.getString("last_update");
            String region = r.getString("province");
            int confirmed = data.getInt("confirmed");
            int deaths = data.getInt("deaths");
            float fatality_rate = data.getFloat("fatality_rate");
            String info = "Last update : " + last_update + "\n" + region + "\n\t confirmed: " + confirmed
                    + "\n\t deaths: " + deaths + "\n\t fatality_rate: " + fatality_rate;
            System.out.println(info + "\n");
            f.write(info + "\n\n");
        }

        f.close();

        System.out.println("\n");
    }
}
