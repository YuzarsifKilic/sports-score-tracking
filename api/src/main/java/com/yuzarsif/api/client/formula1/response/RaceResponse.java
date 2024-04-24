package com.yuzarsif.api.client.formula1.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class RaceResponse {

    public String get;
    public Parameters parameters;
    public ArrayList<Object> errors;
    public int results;
    public ArrayList<Response> response;

    @Data
    public static class Circuit {
        public int id;
        public String name;
        public String image;
    }

    @Data
    public static class Competition {
        public int id;
        public String name;
        public Location location;
    }

    @Data
    public static class Driver {
        public int id;
    }

    @Data
    public static class FastestLap {
        public Driver driver;
        public String time;
    }

    @Data
    public static class Laps {
        public Object current;
        public int total;
    }

    @Data
    public static class Location {
        public String country;
        public String city;
    }

    @Data
    public static class Parameters {
        public String season;
        public String type;
    }

    @Data
    public static class Response {
        public int id;
        public Competition competition;
        public Circuit circuit;
        public int season;
        public String type;
        public Laps laps;
        public FastestLap fastest_lap;
        public String distance;
        public String timezone;
        public String date;
        public Object weather;
        public String status;
    }
}
