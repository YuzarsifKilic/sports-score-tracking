package com.yuzarsif.api.client.formula1.response;

import lombok.Data;

import java.util.ArrayList;

@Data
public class RankingResponse {

    public String get;
    public Parameters parameters;
    public ArrayList<Object> errors;
    public int results;
    public ArrayList<Response> response;

    @Data
    public static class Driver {
        public int id;
        public String name;
        public String abbr;
        public int number;
        public String image;
    }

    @Data
    public static class Parameters {
        public String race;
    }

    @Data
    public static class Race {
        public int id;
    }

    @Data
    public static class Response {
        public Race race;
        public Driver driver;
        public Team team;
        public int position;
        public String time;
        public int laps;
        public String grid;
        public int pits;
        public Object gap;
    }

    @Data
    public static class Team {
        public int id;
        public String name;
        public String logo;
    }
}
