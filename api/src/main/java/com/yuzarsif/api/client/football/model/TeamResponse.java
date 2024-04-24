package com.yuzarsif.api.client.football.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class TeamResponse {

    public ArrayList<Response> response;

    @Data
    public static class Response {
        public Team team;
        public Venue venue;
    }

    @Data
    public static class Team {
        public int id;
        public String name;
        public String code;
        public String country;
        public int founded;
        public boolean national;
        public String logo;
    }

    @Data
    public static class Venue {
        public int id;
        public String name;
        public String address;
        public String city;
        public int capacity;
        public String surface;
        public String image;
    }
}
