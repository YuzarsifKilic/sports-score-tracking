package com.yuzarsif.api.client.football.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class EventResponse {

    public String get;
    public Parameters parameters;
    public ArrayList<Object> errors;
    public int results;
    public Paging paging;
    public ArrayList<Response> response;

    @Data
    public static class Assist {
        public int id;
        public String name;
    }

    @Data
    public static class Paging {
        public int current;
        public int total;
    }

    @Data
    public static class Parameters {
        public String fixture;
    }

    @Data
    public static class Player {
        public int id;
        public String name;
    }

    @Data
    public static class Response {
        public Time time;
        public Team team;
        public Player player;
        public Assist assist;
        public String type;
        public String detail;
        public String comments;
    }

    @Data
    public static class Team {
        public int id;
        public String name;
        public String logo;
    }

    @Data
    public static class Time {
        public int elapsed;
        public int extra;
    }
}
