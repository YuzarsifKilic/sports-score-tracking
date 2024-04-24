package com.yuzarsif.api.client.football.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class InjuriesResponse {

    public ArrayList<Response> response;

    @Data
    public static class Fixture {
        public int id;
        public String timezone;
        public Date date;
        public int timestamp;
    }

    @Data
    public static class League {
        public int id;
        public int season;
        public String name;
        public String country;
        public String logo;
        public String flag;
    }

    @Data
    public static class Player {
        public int id;
        public String name;
        public String photo;
        public String type;
        public String reason;
    }

    @Data
    public static class Response {
        public Player player;
        public Team team;
        public Fixture fixture;
        public League league;
    }

    @Data
    public static class Team {
        public int id;
        public String name;
        public String logo;
    }

}
