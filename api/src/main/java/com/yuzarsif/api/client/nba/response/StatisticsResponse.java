package com.yuzarsif.api.client.nba.response;

import lombok.Data;

import java.util.ArrayList;

@Data
public class StatisticsResponse {

    public String get;
    public Parameters parameters;
    public ArrayList<Object> errors;
    public int results;
    public ArrayList<Response> response;

    @Data
    public static class Game {
        public int id;
    }

    @Data
    public static class Parameters {
        public String game;
    }

    @Data
    public static class Player {
        public int id;
        public String firstname;
        public String lastname;
    }

    @Data
    public static class Response {
        public Player player;
        public Team team;
        public Game game;
        public int points;
        public String pos;
        public String min;
        public int fgm;
        public int fga;
        public String fgp;
        public int ftm;
        public int fta;
        public String ftp;
        public int tpm;
        public int tpa;
        public String tpp;
        public int offReb;
        public int defReb;
        public int totReb;
        public int assists;
        public int pFouls;
        public int steals;
        public int turnovers;
        public int blocks;
        public String plusMinus;
        public String comment;
    }

    @Data
    public static class Team {
        public int id;
        public String name;
        public String nickname;
        public String code;
        public String logo;
    }
}
