package com.yuzarsif.api.client.nba;

import com.yuzarsif.api.client.nba.response.LeagueResponse;
import com.yuzarsif.api.client.nba.response.TeamResponse;
import com.yuzarsif.api.config.RapidApiProperties;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TeamClient {

    private final RapidApiProperties rapidApiProperties;
    private final RestTemplate restTemplate;

    public TeamClient(RapidApiProperties rapidApiProperties, RestTemplate restTemplate) {
        this.rapidApiProperties = rapidApiProperties;
        this.restTemplate = restTemplate;
    }

    public TeamResponse findTeams() {
        String url = String.format("https://%s/teams", rapidApiProperties.getXRapidApiNbaHost());

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("x-rapidapi-host", rapidApiProperties.getXRapidApiNbaHost());
        headers.set("x-rapidapi-key", rapidApiProperties.getXRapidApiKey());

        HttpEntity entity = new HttpEntity(headers);

        try {
            ResponseEntity<TeamResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, TeamResponse.class);
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new EntityNotFoundException("Country not found");
    }
}
