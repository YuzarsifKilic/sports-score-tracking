package com.yuzarsif.api.client.football.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class StatisticResponse {

    public String get;
    public Parameters parameters;
    public ArrayList<Object> errors;
    public int results;
    public Paging paging;
    public ArrayList<Response> response;

    @Data
    public static class Paging {
        public int current;
        public int total;
    }

    @Data
    public static class Parameters {
        public String fixture;
    }

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
