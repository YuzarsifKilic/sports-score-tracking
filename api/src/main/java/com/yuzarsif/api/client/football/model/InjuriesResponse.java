package com.yuzarsif.api.client.football.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InjuriesResponse {

    public ArrayList<Response> response;

    @Data
@AllArgsConstructor
@NoArgsConstructor
    public static class Fixture {
        public int id;
        public String timezone;
        public Date date;
        public int timestamp;
    }

    @Data
@AllArgsConstructor
@NoArgsConstructor
    public static class League {
        public int id;
        public int season;
        public String name;
        public String country;
        public String logo;
        public String flag;
    }

    @Data
@AllArgsConstructor
@NoArgsConstructor
    public static class Player {
        public int id;
        public String name;
        public String photo;
        public String type;
        public String reason;
    }

    @Data
@AllArgsConstructor
@NoArgsConstructor
    public static class Response {
        public Player player;
        public Team team;
        public Fixture fixture;
        public League league;
    }

    @Data
@AllArgsConstructor
@NoArgsConstructor
    public static class Team {
        public int id;
        public String name;
        public String logo;
    }

}
