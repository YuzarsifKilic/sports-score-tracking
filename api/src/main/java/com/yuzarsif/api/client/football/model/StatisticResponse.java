package com.yuzarsif.api.client.football.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticResponse {

    public ArrayList<Response> response;

    @Data
@AllArgsConstructor
@NoArgsConstructor
    public static class Response {
        public Team team;
        public ArrayList<Statistic> statistics;
    }

    @Data
@AllArgsConstructor
@NoArgsConstructor
    public static class Statistic {
        public String type;
        public Object value;
    }

    @Data
@AllArgsConstructor
@NoArgsConstructor
    public static class Team {
        public int id;
        public String name;
        public String logo;
    }
}
