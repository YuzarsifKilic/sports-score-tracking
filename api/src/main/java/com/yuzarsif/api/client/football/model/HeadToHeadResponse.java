package com.yuzarsif.api.client.football.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class HeadToHeadResponse {

    public String get;
    public Parameters parameters;
    public ArrayList<Object> errors;
    public int results;
    public Paging paging;
    public ArrayList<Response> response;

    @Data
    public static class Away {
        public int id;
        public String name;
        public String logo;
        public boolean winner;
    }

    @Data
    public static class Extratime {
        public Object home;
        public Object away;
    }

    @Data
    public static class Fixture {
        public int id;
        public String referee;
        public String timezone;
        public Date date;
        public int timestamp;
        public Periods periods;
        public Venue venue;
        public Status status;
    }

    @Data
    public static class Fulltime {
        public int home;
        public int away;
    }

    @Data
    public static class Goals {
        public int home;
        public int away;
    }

    @Data
    public static class Halftime {
        public int home;
        public int away;
    }

    @Data
    public static class Home {
        public int id;
        public String name;
        public String logo;
        public boolean winner;
    }

    @Data
    public static class League {
        public int id;
        public String name;
        public String country;
        public String logo;
        public String flag;
        public int season;
        public String round;
    }

    @Data
    public static class Paging {
        public int current;
        public int total;
    }

    @Data
    public static class Parameters {
        public String h2h;
    }

    @Data
    public static class Penalty {
        public Object home;
        public Object away;
    }

    @Data
    public static class Periods {
        public int first;
        public int second;
    }

    @Data
    public static class Response {
        public Fixture fixture;
        public League league;
        public Teams teams;
        public Goals goals;
        public Score score;
    }

    @Data
    public static class Score {
        public Halftime halftime;
        public Fulltime fulltime;
        public Extratime extratime;
        public Penalty penalty;
    }

    @Data
    public static class Status {
        @JsonProperty("long")
        public String mylong;
        @JsonProperty("short")
        public String myshort;
        public int elapsed;
    }

    @Data
    public static class Teams {
        public Home home;
        public Away away;
    }

    @Data
    public static class Venue {
        public int id;
        public String name;
        public String city;
    }

}
