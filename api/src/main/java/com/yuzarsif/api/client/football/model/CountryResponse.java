package com.yuzarsif.api.client.football.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class CountryResponse {

    public ArrayList<Response> response;

    @Data
    public static class Response {
        public String name;
        public String code;
        public String flag;
    }
}
