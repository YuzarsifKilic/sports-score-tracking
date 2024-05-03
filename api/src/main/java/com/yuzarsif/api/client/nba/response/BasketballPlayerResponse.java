package com.yuzarsif.api.client.nba.response;

import lombok.Data;

import java.util.List;

@Data
public class BasketballPlayerResponse {

    public List<Response> response;
    
    @Data
    public static class Response {
        public int id;
        public String firstname;
        public String lastname;
        public Birth birth;
        public NBA nba;
        public Height height;
        public Weight weight;
        public String college;
        public String affiliation;
        public Leagues leagues;
    }
    

    @Data
    public static class Birth {
        public String date;
        public String country;
    }

    @Data
    public static class NBA {
        public int start;
        public int pro;
    }

    @Data
    public static class Height {
        public String feets;
        public String inches;
        public double meters;
    }

    @Data
    public static class Weight {
        public int pounds;
        public double kilograms;
    }

    @Data
    public static class Leagues {
        public Standard standard;

        @Data
        public static class Standard {
            public int jersey;
            public boolean active;
            public String pos;
        }
    }
}
