package com.yuzarsif.api.client.nba.response;

import lombok.Data;

import java.util.ArrayList;

@Data
public class StandingsResponse {

    public ArrayList<Response> response;

    @Data
    public static class Conference {
        public String name;
        public int rank;
        public int win;
        public int loss;
    }

    @Data
    public static class Division {
        public String name;
        public int rank;
        public int win;
        public int loss;
        public String gamesBehind;
    }

    @Data
    public static class Loss {
        public int home;
        public int away;
        public int total;
        public String percentage;
        public int lastTen;
    }

    @Data
    public static class Parameters {
        public String season;
        public String league;
    }

    @Data
    public static class Response {
        public String league;
        public int season;
        public Team team;
        public Conference conference;
        public Division division;
        public Win win;
        public Loss loss;
        public String gamesBehind;
        public int streak;
        public boolean winStreak;
        public Object tieBreakerPoints;
    }

    @Data
    public static class Team {
        public int id;
        public String name;
        public String nickname;
        public String code;
        public String logo;
    }

    @Data
    public static class Win {
        public int home;
        public int away;
        public int total;
        public String percentage;
        public int lastTen;
    }
}
