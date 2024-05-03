package com.yuzarsif.api.client.nba.response;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class StatisticsResponse {
    public ArrayList<Response> response;

    @Data
    public static class Game {
        public Integer id;
    }

    @Data
    public static class Player {
        public Integer id;
        public String firstname;
        public String lastname;
    }

    @Data
    @Builder
    public static class Response {
        public Player player;
        public Team team;
        public Game game;
        public Integer points;
        public String pos;
        public String min;
        public Integer fgm;
        public Integer fga;
        public String fgp;
        public Integer ftm;
        public Integer fta;
        public String ftp;
        public Integer tpm;
        public Integer tpa;
        public String tpp;
        public Integer offReb;
        public Integer defReb;
        public Integer totReb;
        public Integer assists;
        public Integer pFouls;
        public Integer steals;
        public Integer turnovers;
        public Integer blocks;
        public String plusMinus;
        public String comment;
    }

    @Data
    @Builder
    public static class CustomResponse {
        public Player player;
        public Team team;
        public Game game;
        public Integer points;
        public Set<String> pos;
        public String min;
        public Integer offReb;
        public Integer defReb;
        public Integer totReb;
        public Integer assists;
        public Integer steals;
        public Integer blocks;
    }

    @Data
    public static class Team {
        public Integer id;
        public String name;
        public String nickname;
        public String code;
        public String logo;
    }
}
