package com.yuzarsif.api.client.football;

import com.yuzarsif.api.client.football.model.FixtureCustomResponse;
import com.yuzarsif.api.client.football.model.FixtureResponse;
import com.yuzarsif.api.config.RapidApiProperties;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Service
public class FixtureClient {

    private final RapidApiProperties rapidApiProperties;
    private final RestTemplate restTemplate;

    public FixtureClient(RapidApiProperties rapidApiProperties, RestTemplate restTemplate) {
        this.rapidApiProperties = rapidApiProperties;
        this.restTemplate = restTemplate;
    }

    public FixtureResponse findFixtures(Integer season, Integer teamId) {
        String url = String.format("https://%s/fixtures?season=%s&team=%s", rapidApiProperties.getXRapidApiFootballHost(), season, teamId);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("x-rapidapi-host", rapidApiProperties.getXRapidApiFootballHost());
        headers.set("x-rapidapi-key", rapidApiProperties.getXRapidApiKey());

        HttpEntity entity = new HttpEntity(headers);

        try {
            ResponseEntity<FixtureResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, FixtureResponse.class);
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new EntityNotFoundException("Country not found");
    }

    public FixtureResponse.Response findFixturesById(Integer fixtureId) {
        String url = String.format("https://%s/fixtures?id=%s", rapidApiProperties.getXRapidApiFootballHost(), fixtureId);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("x-rapidapi-host", rapidApiProperties.getXRapidApiFootballHost());
        headers.set("x-rapidapi-key", rapidApiProperties.getXRapidApiKey());

        HttpEntity entity = new HttpEntity(headers);

        try {
            ResponseEntity<FixtureResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, FixtureResponse.class);
            FixtureResponse body = response.getBody();
            FixtureResponse.Response fixtureResponse = body.getResponse().get(0);
            return fixtureResponse;
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new EntityNotFoundException("Country not found");
    }

    public List<FixtureResponse.Response> find5FixturesByTeamId(Integer teamId) {
        String url = String.format("https://%s/fixtures?team=%s&last=5", rapidApiProperties.getXRapidApiFootballHost(), teamId);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("x-rapidapi-host", rapidApiProperties.getXRapidApiFootballHost());
        headers.set("x-rapidapi-key", rapidApiProperties.getXRapidApiKey());

        HttpEntity entity = new HttpEntity(headers);

        try {
            ResponseEntity<FixtureResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, FixtureResponse.class);
            FixtureResponse body = response.getBody();
            assert body != null;
            return body.getResponse();
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new EntityNotFoundException("Country not found");
    }

    public List<FixtureCustomResponse.Response> findFixturesByDate(String date) {
        String url = String.format("https://%s/fixtures?date=%s", rapidApiProperties.getXRapidApiFootballHost(), date);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("x-rapidapi-host", rapidApiProperties.getXRapidApiFootballHost());
        headers.set("x-rapidapi-key", rapidApiProperties.getXRapidApiKey());

        HttpEntity entity = new HttpEntity(headers);

        try {
            ResponseEntity<FixtureResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, FixtureResponse.class);
            FixtureResponse body = response.getBody();
            List<FixtureCustomResponse.Response> responses = convertToCustomResponse(body.getResponse());
            int order = 0;
            if (changeOrder(order, "Turkey", "Süper Lig", responses)){
                order++;
            }
            if (changeOrder(order, "England", "Premier League", responses)){
                order++;
            }
            if (changeOrder(order, "Italy", "Serie A", responses)){
                order++;
            }
            if (changeOrder(order, "Germany", "Bundesliga", responses)){
                order++;
            }
            if (changeOrder(order, "Spain", "La Liga", responses)){
                order++;
            }
            return responses;
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new EntityNotFoundException("Country not found");
    }

    private List<FixtureCustomResponse.Response> convertToCustomResponse(List<FixtureResponse.Response> fixtureResponses) {
        HashSet<String> leagues = new HashSet<>();
        List<FixtureCustomResponse.Response> responseList = new ArrayList<>();

        for (FixtureResponse.Response fixtureResponse: fixtureResponses) {
            if (!leagues.contains(fixtureResponse.getLeague().getName() + " " + fixtureResponse.getLeague().getCountry())) {
                leagues.add(fixtureResponse.getLeague().getName() + " " + fixtureResponse.getLeague().getCountry());
                FixtureCustomResponse.Response response2 = FixtureCustomResponse.Response.builder().build();
                response2.setLeague(fixtureResponse.getLeague());
                FixtureCustomResponse.CustomResponse customResponse = FixtureCustomResponse.CustomResponse
                        .builder()
                        .score(fixtureResponse.getScore())
                        .fixture(fixtureResponse.getFixture())
                        .goals(fixtureResponse.getGoals())
                        .teams(fixtureResponse.getTeams())
                        .build();
                response2.setResponse(new ArrayList<>());
                response2.getResponse().add(customResponse);
                responseList.add(response2);
            } else {
                for (FixtureCustomResponse.Response response2 : responseList) {
                    if (response2.getLeague().getName().equals(fixtureResponse.getLeague().getName()) && response2.getLeague().getCountry().equals(fixtureResponse.getLeague().getCountry())) {
                        FixtureCustomResponse.CustomResponse customResponse = FixtureCustomResponse.CustomResponse
                                .builder()
                                .score(fixtureResponse.getScore())
                                .fixture(fixtureResponse.getFixture())
                                .goals(fixtureResponse.getGoals())
                                .teams(fixtureResponse.getTeams())
                                .build();
                        response2.getResponse().add(customResponse);
                    }
                }
            }
        }
        return responseList;
    }

    private boolean changeOrder(int number, String countryName, String leagueName, List<FixtureCustomResponse.Response> response) {
        for (int i = 0; i < response.size(); i++) {
            if (response.get(i).getLeague().getCountry().equals(countryName) && response.get(i).getLeague().getName().equals(leagueName)) {
                Collections.swap(response, i, number);
                return true;
            }
        }
        return false;
    }
}
