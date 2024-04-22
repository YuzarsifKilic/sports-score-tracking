package com.yuzarsif.api.client.football.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class LineUpsResponse {

    public String get;
    public Parameters parameters;
    public ArrayList<Object> errors;
    public int results;
    public Paging paging;
    public ArrayList<Response> response;

    @Data
    public static class Coach {
        public int id;
        public String name;
        public String photo;
    }

    @Data
    public static class Colors {
        public Player player;
        public Goalkeeper goalkeeper;
    }

    @Data
    public static class Goalkeeper {
        public String primary;
        public String number;
        public String border;
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
        public String primary;
        public String number;
        public String border;
        public int id;
        public String name;
        public String pos;
        public String grid;
    }

    @Data
    public static class Response {
        public Team team;
        public Coach coach;
        public String formation;
        public ArrayList<StartXI> startXI;
        public ArrayList<Substitute> substitutes;
    }

    @Data
    public static class StartXI {
        public Player player;
    }

    @Data
    public static class Substitute {
        public Player player;
    }

    @Data
    public static class Team {
        public int id;
        public String name;
        public String logo;
        public Colors colors;
    }
}
