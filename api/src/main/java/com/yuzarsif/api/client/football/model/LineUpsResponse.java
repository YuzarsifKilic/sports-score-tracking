package com.yuzarsif.api.client.football.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineUpsResponse {

    public ArrayList<Response> response;

    @Data
@AllArgsConstructor
@NoArgsConstructor
    public static class Coach {
        public int id;
        public String name;
        public String photo;
    }

    @Data
@AllArgsConstructor
@NoArgsConstructor
    public static class Colors {
        public Player player;
        public Goalkeeper goalkeeper;
    }

    @Data
@AllArgsConstructor
@NoArgsConstructor
    public static class Goalkeeper {
        public String primary;
        public String number;
        public String border;
    }

    @Data
@AllArgsConstructor
@NoArgsConstructor
    public static class Player {
        public String primary;
        public String number;
        public String border;
        public int id;
        public String name;
        public String pos;
        public String grid;
    }

    @Data
@AllArgsConstructor
@NoArgsConstructor
    public static class Response {
        public Team team;
        public Coach coach;
        public String formation;
        public ArrayList<StartXI> startXI;
        public ArrayList<Substitute> substitutes;
    }

    @Data
@AllArgsConstructor
@NoArgsConstructor
    public static class StartXI {
        public Player player;
    }

    @Data
@AllArgsConstructor
@NoArgsConstructor
    public static class Substitute {
        public Player player;
    }

    @Data
@AllArgsConstructor
@NoArgsConstructor
    public static class Team {
        public int id;
        public String name;
        public String logo;
        public Colors colors;
    }
}
