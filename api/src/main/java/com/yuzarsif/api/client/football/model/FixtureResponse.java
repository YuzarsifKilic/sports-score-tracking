package com.yuzarsif.api.client.football.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FixtureResponse {

    public ArrayList<Response> response;

    @Data
@AllArgsConstructor
@NoArgsConstructor
    public static class Away {
        public int id;
        public String name;
        public String logo;
        public boolean winner;
    }

    @Data
@AllArgsConstructor
@NoArgsConstructor
    public static class Extratime {
        public int home;
        public int away;
    }

    @Data
@AllArgsConstructor
@NoArgsConstructor
    public static class Fixture {
        public int id;
        public String referee;
        public String timezone;
        public String date;
        public int timestamp;
        public Periods periods;
        public Venue venue;
        public Status status;
    }

    @Data
@AllArgsConstructor
@NoArgsConstructor
    public static class Fulltime {
        public int home;
        public int away;
    }

    @Data
@AllArgsConstructor
@NoArgsConstructor
    public static class Goals {
        public int home;
        public int away;
    }

    @Data
@AllArgsConstructor
@NoArgsConstructor
    public static class Halftime {
        public int home;
        public int away;
    }

    @Data
@AllArgsConstructor
@NoArgsConstructor
    public static class Home {
        public int id;
        public String name;
        public String logo;
        public boolean winner;
    }

    @Data
@AllArgsConstructor
@NoArgsConstructor
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
@AllArgsConstructor
@NoArgsConstructor
    public static class Penalty {
        public int home;
        public int away;
    }

    @Data
@AllArgsConstructor
@NoArgsConstructor
    public static class Periods {
        public int first;
        public int second;
    }

    @Data
@AllArgsConstructor
@NoArgsConstructor
    public static class Response {
        public Fixture fixture;
        public League league;
        public Teams teams;
        public Goals goals;
        public Score score;
    }

    @Data
@AllArgsConstructor
@NoArgsConstructor
    public static class Score {
        public Halftime halftime;
        public Fulltime fulltime;
        public Extratime extratime;
        public Penalty penalty;
    }

    @Data
@AllArgsConstructor
@NoArgsConstructor
    public static class Status {
        @JsonProperty("long")
        public String mylong;
        @JsonProperty("short")
        public String myshort;
        public int elapsed;
    }

    @Data
@AllArgsConstructor
@NoArgsConstructor
    public static class Teams {
        public Home home;
        public Away away;
    }

    @Data
@AllArgsConstructor
@NoArgsConstructor
    public static class Venue {
        public int id;
        public String name;
        public String city;
    }
}
