package com.yuzarsif.api.client.football.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventResponse {

    public ArrayList<Response> response;

    @Data
@AllArgsConstructor
@NoArgsConstructor
    public static class Assist {
        public int id;
        public String name;
    }

    @Data
@AllArgsConstructor
@NoArgsConstructor
    public static class Player {
        public int id;
        public String name;
    }

    @Data
@AllArgsConstructor
@NoArgsConstructor
    public static class Response {
        public Time time;
        public Team team;
        public Player player;
        public Assist assist;
        public String type;
        public String detail;
        public String comments;
    }

    @Data
@AllArgsConstructor
@NoArgsConstructor
    public static class Team {
        public int id;
        public String name;
        public String logo;
    }

    @Data
@AllArgsConstructor
@NoArgsConstructor
    public static class Time {
        public int elapsed;
        public int extra;
    }
}
