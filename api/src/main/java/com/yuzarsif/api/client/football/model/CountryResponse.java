package com.yuzarsif.api.client.football.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class CountryResponse {

    public String get;
    public ArrayList<Object> parameters;
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
    public static class Response {
        public String name;
        public String code;
        public String flag;
    }
}
