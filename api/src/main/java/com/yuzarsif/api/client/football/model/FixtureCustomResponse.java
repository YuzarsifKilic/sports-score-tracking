package com.yuzarsif.api.client.football.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FixtureCustomResponse {

    public ArrayList<Response> response;


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        public FixtureResponse.League league;
        public ArrayList<CustomResponse> response;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CustomResponse {
        public FixtureResponse.Fixture fixture;
        public FixtureResponse.Teams teams;
        public FixtureResponse.Goals goals;
        public FixtureResponse.Score score;
    }
}
