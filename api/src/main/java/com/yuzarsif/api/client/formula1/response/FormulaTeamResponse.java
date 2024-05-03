package com.yuzarsif.api.client.formula1.response;

import lombok.Data;

import java.util.List;

@Data
public class FormulaTeamResponse {

    public List<Response> response;

    @Data
    public static class Response {
        public int id;
        public String name;
        public String logo;
        public String base;
        public Integer first_team_entry;
        public Integer world_championships;
        public HighestRaceFinish highest_race_finish;
        public Integer pole_positions;
        public Integer fastest_laps;
        public String president;
        public String director;
        public String technical_manager;
        public String chassis;
        public String engine;
        public String tyres;
    }

    @Data
    public static class HighestRaceFinish {
        public Integer position;
        public Integer number;
    }
}
