package com.yuzarsif.api.client.nba.response;

import lombok.Data;

import java.util.ArrayList;

@Data
public class LeagueResponse {

    public String get;
    public ArrayList<Object> parameters;
    public ArrayList<Object> errors;
    public int results;
    public ArrayList<String> response;
}
