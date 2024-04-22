package com.yuzarsif.api.client.nba.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class GameResponse {

    public String get;
    public Parameters parameters;
    public ArrayList<Object> errors;
    public int results;
    public ArrayList<Response> response;

    @Data
    public static class Arena {
        public String name;
        public String city;
        public String state;
        public String country;
    }

    @Data
    public static class Date {
        public Date start;
        public Date end;
        public String duration;
    }

    @Data
    public static class Home {
        public int id;
        public String name;
        public String nickname;
        public String code;
        public String logo;
        public int win;
        public int loss;
        public Series series;
        public ArrayList<String> linescore;
        public int points;
    }

    @Data
    public static class Parameters {
        public String season;
    }

    @Data
    public static class Periods {
        public int current;
        public int total;
        public boolean endOfPeriod;
    }

    @Data
    public static class Response {
        public int id;
        public String league;
        public int season;
        public Date date;
        public int stage;
        public Status status;
        public Periods periods;
        public Arena arena;
        public Teams teams;
        public Scores scores;
        public ArrayList<String> officials;
        public int timesTied;
        public int leadChanges;
        public Object nugget;
    }

    @Data
    public static class Scores {
        public Visitors visitors;
        public Home home;
    }

    @Data
    public static class Series {
        public int win;
        public int loss;
    }

    @Data
    public static class Status {
        public Object clock;
        public boolean halftime;
        @JsonProperty("short")
        public int myshort;
        @JsonProperty("long")
        public String mylong;
    }

    @Data
    public static class Teams {
        public Visitors visitors;
        public Home home;
    }

    @Data
    public static class Visitors {
        public int id;
        public String name;
        public String nickname;
        public String code;
        public String logo;
        public int win;
        public int loss;
        public Series series;
        public ArrayList<String> linescore;
        public int points;
    }
}
