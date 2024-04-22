package com.yuzarsif.api.client.nba.response;

import lombok.Data;

import java.util.ArrayList;

@Data
public class TeamResponse {

    public String get;
    public ArrayList<Object> parameters;
    public ArrayList<Object> errors;
    public int results;
    public ArrayList<Response> response;

    @Data
    public static class Africa {
        public Object conference;
        public Object division;
    }

    @Data
    public static class Leagues {
        public Standard standard;
        public Vegas vegas;
        public Utah utah;
        public Sacramento sacramento;
        public Africa africa;
    }

    @Data
    public static class Response {
        public int id;
        public String name;
        public String nickname;
        public String code;
        public String city;
        public String logo;
        public boolean allStar;
        public boolean nbaFranchise;
        public Leagues leagues;
    }

    @Data
    public static class Sacramento {
        public String conference;
        public String division;
    }

    @Data
    public static class Standard {
        public String conference;
        public String division;
    }

    @Data
    public static class Utah {
        public String conference;
        public String division;
    }

    @Data
    public static class Vegas {
        public String conference;
        public String division;
    }

}
