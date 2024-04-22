package com.yuzarsif.api.client.formula1.response;

import lombok.Data;

import java.util.ArrayList;

@Data
public class DriversResponse {

    public String get;
    public Parameters parameters;
    public ArrayList<Object> errors;
    public int results;
    public ArrayList<Response> response;

    @Data
    public static class Country {
        public String name;
        public String code;
    }

    @Data
    public static class HighestRaceFinish {
        public int position;
        public int number;
    }

    @Data
    public static class Parameters {
        public String search;
    }

    @Data
    public static class Response {
        public int id;
        public String name;
        public String abbr;
        public String image;
        public String nationality;
        public Country country;
        public String birthdate;
        public String birthplace;
        public int number;
        public int grands_prix_entered;
        public int world_championships;
        public int podiums;
        public HighestRaceFinish highest_race_finish;
        public int highest_grid_position;
        public String career_points;
        public ArrayList<Team> teams;
    }

    @Data
    public static class Team {
        public int season;
        public Team team;
    }

    @Data
    public static class Team2 {
        public int id;
        public String name;
        public String logo;
    }
}
