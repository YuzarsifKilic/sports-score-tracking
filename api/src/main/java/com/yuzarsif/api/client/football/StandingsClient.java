package com.yuzarsif.api.client.football;

import com.yuzarsif.api.client.football.model.CountryResponse;
import com.yuzarsif.api.client.football.model.StandingsResponse;
import com.yuzarsif.api.config.RapidApiProperties;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StandingsClient {

    private final RapidApiProperties rapidApiProperties;
    private final RestTemplate restTemplate;

    public StandingsClient(RapidApiProperties rapidApiProperties, RestTemplate restTemplate) {
        this.rapidApiProperties = rapidApiProperties;
        this.restTemplate = restTemplate;
    }

    public StandingsResponse findStandings(Integer season, Integer leagueId) {
        String url = String.format("https://%s/standings?season=%s&league=%s", rapidApiProperties.getXRapidApiFootballHost(), season, leagueId);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("x-rapidapi-host", rapidApiProperties.getXRapidApiFootballHost());
        headers.set("x-rapidapi-key", rapidApiProperties.getXRapidApiKey());

        HttpEntity entity = new HttpEntity(headers);

        try {
            ResponseEntity<StandingsResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, StandingsResponse.class);
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new EntityNotFoundException("Country not found");
    }
}