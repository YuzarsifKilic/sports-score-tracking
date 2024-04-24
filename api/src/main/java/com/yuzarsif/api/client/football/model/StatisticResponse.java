package com.yuzarsif.api.client.football.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class StatisticResponse {

    public ArrayList<Response> response;

    @Data
    public static class Response {
        public Team team;
        public ArrayList<Statistic> statistics;
    }

    @Data
    public static class Statistic {
        public String type;
        public Object value;
    }

    @Data
    public static class Team {
        public int id;
        public String name;
        public String logo;
    }
}
