package com.yuzarsif.api.client.football.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeagueResponse {

    public ArrayList<Response> response;

    @Data
@AllArgsConstructor
@NoArgsConstructor
    public static class Country {
        public String name;
        public String code;
        public String flag;
    }

    @Data
@AllArgsConstructor
@NoArgsConstructor
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
@AllArgsConstructor
@NoArgsConstructor
    public static class Fixtures {
        public boolean events;
        public boolean lineups;
        public boolean statistics_fixtures;
        public boolean statistics_players;
    }

    @Data
@AllArgsConstructor
@NoArgsConstructor
    public static class League {
        public int id;
        public String name;
        public String type;
        public String logo;
    }

    @Data
@AllArgsConstructor
@NoArgsConstructor
    public static class Response {
        public League league;
        public Country country;
        public ArrayList<Season> seasons;
    }

    @Data
@AllArgsConstructor
@NoArgsConstructor
    public static class Season{
        public int year;
        public String start;
        public String end;
        public boolean current;
        public Coverage coverage;
    }
}
