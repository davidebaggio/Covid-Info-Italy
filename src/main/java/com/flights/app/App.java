package com.flights.app;

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

        GUI g = new GUI(array);
        g.parseInfo();

        System.out.println("\n");
    }
}
