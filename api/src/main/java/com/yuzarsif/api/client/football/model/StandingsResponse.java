package com.yuzarsif.api.client.football.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class StandingsResponse {

    public String get;
    public Parameters parameters;
    public ArrayList<Object> errors;
    public int results;
    public Paging paging;
    public ArrayList<Response> response;

    @Data
    public static class League {
        public int id;
        public String name;
        public String country;
        public String logo;
        public String flag;
        public int season;
        public ArrayList<ArrayList<Standings>> standings;
    }

    @Data
    public static class Paging {
        public int current;
        public int total;
    }

    @Data
    public static class Parameters {
        public String season;
        public String league;
    }

    @Data
    public static class Response {
        public League league;
    }

    @Data
    public static class Standings {
        public int rank;
        public Team team;
        public int points;
        public int goalsDiff;
        public String group;
        public String form;
        public String status;
        public String description;
        public All all;
        public Home home;
        public Away away;
        public Date update;
    }

    @Data
    public static class Team{
        public int id;
        public String name;
        public String logo;
    }

    @Data
    public static class All{
        public int played;
        public int win;
        public int draw;
        public int lose;
        public Goals goals;
    }

    @Data
    public static class Away {
        public int played;
        public int win;
        public int draw;
        public int lose;
        public Goals goals;
    }

    @Data
    public static class Goals {
        @JsonProperty("for")
        public int myfor;
        public int against;
    }

    @Data
    public static class Home {
        public int played;
        public int win;
        public int draw;
        public int lose;
        public Goals goals;
    }
}
