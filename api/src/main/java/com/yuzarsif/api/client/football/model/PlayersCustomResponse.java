package com.yuzarsif.api.client.football.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayersCustomResponse {

    public Paging paging;
    public ArrayList<Response> response;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Paging {
        public Integer current;
        public Integer total;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Player {
        public Integer id;
        public String name;
        public String firstname;
        public String lastname;
        public Integer age;
        public Birth birth;
        public String nationality;
        public String height;
        public String weight;
        public boolean injured;
        public String photo;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Birth {
        public String date;
        public String place;
        public String country;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Cards {
        public Integer yellow;
        public Integer yellowred;
        public Integer red;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Duels {
        public Integer total;
        public Integer won;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Fouls {
        public Integer drawn;
        public Integer committed;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Games {
        public Integer appearences;
        public Integer lineups;
        public Integer minutes;
        public Object number;
        public String position;
        public String rating;
        public boolean captain;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Goals {
        public Integer total;
        public Integer conceded;
        public Integer assists;
        public Integer saves;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class League {
        public Integer id;
        public String name;
        public String country;
        public String logo;
        public String flag;
        public String season;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Passes {
        public Integer total;
        public Integer key;
        public Integer accuracy;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Penalty {
        public Integer won;
        public Integer commited;
        public Integer scored;
        public Integer missed;
        public Integer saved;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Shots {
        public Integer total;
        public Integer on;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Statistic {
        public Team team;
        public League league;
        public Games games;
        public Substitutes substitutes;
        public Shots shots;
        public Goals goals;
        public Passes passes;
        public Tackles tackles;
        public Duels duels;
        public Fouls fouls;
        public Cards cards;
        public Penalty penalty;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Substitutes {
        public Integer in;
        public Integer out;
        public Integer bench;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Tackles {
        public Integer total;
        public Integer blocks;
        public Integer Integererceptions;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Team {
        public Integer id;
        public String name;
        public String logo;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        public Player player;
        public ArrayList<Statistic> statistics;
    }
}
