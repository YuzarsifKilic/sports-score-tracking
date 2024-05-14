package com.yuzarsif.api.client.football.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamResponse {

    public ArrayList<Response> response;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        public Team team;
        public Venue venue;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
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
    @AllArgsConstructor
    @NoArgsConstructor
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
