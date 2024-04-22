package com.yuzarsif.api.client.football;

import com.yuzarsif.api.client.football.model.LeagueResponse;
import com.yuzarsif.api.config.RapidApiProperties;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LeagueClient {

    private final RapidApiProperties rapidApiProperties;
    private final RestTemplate restTemplate;

    public LeagueClient(RapidApiProperties rapidApiProperties, RestTemplate restTemplate) {
        this.rapidApiProperties = rapidApiProperties;
        this.restTemplate = restTemplate;
    }

    public LeagueResponse findLeagues(String countryCode) {
        String url = String.format("https://%s/leagues?code=%s", rapidApiProperties.getXRapidApiFootballHost(), countryCode);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("x-rapidapi-host", rapidApiProperties.getXRapidApiFootballHost());
        headers.set("x-rapidapi-key", rapidApiProperties.getXRapidApiKey());

        HttpEntity entity = new HttpEntity(headers);
        try {
            ResponseEntity<LeagueResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, LeagueResponse.class);
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new EntityNotFoundException("League not found");
    }
}
