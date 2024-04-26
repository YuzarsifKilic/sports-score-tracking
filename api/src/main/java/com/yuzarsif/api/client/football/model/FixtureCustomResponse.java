package com.yuzarsif.api.client.football.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
public class FixtureCustomResponse {

    public ArrayList<Response> response;


    @Data
    @Builder
    public static class Response {
        public FixtureResponse.League league;
        public ArrayList<CustomResponse> response;
    }

    @Data
    @Builder
    public static class CustomResponse {
        public FixtureResponse.Fixture fixture;
        public FixtureResponse.Teams teams;
        public FixtureResponse.Goals goals;
        public FixtureResponse.Score score;
    }
}
