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
public class StandingsResponse {

    public ArrayList<Response> response;

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
        public ArrayList<ArrayList<Standings>> standings;
    }

    @Data
@AllArgsConstructor
@NoArgsConstructor
    public static class Response {
        public League league;
    }

    @Data
@AllArgsConstructor
@NoArgsConstructor
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
@AllArgsConstructor
@NoArgsConstructor
    public static class Team{
        public int id;
        public String name;
        public String logo;
    }

    @Data
@AllArgsConstructor
@NoArgsConstructor
    public static class All{
        public int played;
        public int win;
        public int draw;
        public int lose;
        public Goals goals;
    }

    @Data
@AllArgsConstructor
@NoArgsConstructor
    public static class Away {
        public int played;
        public int win;
        public int draw;
        public int lose;
        public Goals goals;
    }

    @Data
@AllArgsConstructor
@NoArgsConstructor
    public static class Goals {
        @JsonProperty("for")
        public int myfor;
        public int against;
    }

    @Data
@AllArgsConstructor
@NoArgsConstructor
    public static class Home {
        public int played;
        public int win;
        public int draw;
        public int lose;
        public Goals goals;
    }
}
