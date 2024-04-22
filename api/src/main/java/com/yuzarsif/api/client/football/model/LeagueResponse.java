package com.yuzarsif.api.client.football.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class LeagueResponse {

    public String get;
    public Parameters parameters;
    public ArrayList<Object> errors;
    public int results;
    public Paging paging;
    public ArrayList<Response> response;

    @Data
    public static class Country {
        public String name;
        public String code;
        public String flag;
    }

    @Data
    public static class Coverage {
        public Fixtures fixtures;
        public boolean standings;
        public boolean players;
        public boolean top_scorers;
        public boolean top_assists;
        public boolean top_cards;
        public boolean injuries;
        public boolean predictions;
        public boolean odds;
    }

    @Data
    public static class Fixtures {
        public boolean events;
        public boolean lineups;
        public boolean statistics_fixtures;
        public boolean statistics_players;
    }

    @Data
    public static class League {
        public int id;
        public String name;
        public String type;
        public String logo;
    }

    @Data
    public static class Paging {
        public int current;
        public int total;
    }

    @Data
    public static class Parameters {
        public String code;
    }

    @Data
    public static class Response {
        public League league;
        public Country country;
        public ArrayList<Season> seasons;
    }

    @Data
    public static class Season{
        public int year;
        public String start;
        public String end;
        public boolean current;
        public Coverage coverage;
    }
}
