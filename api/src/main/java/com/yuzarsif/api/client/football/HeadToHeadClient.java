package com.yuzarsif.api.client.football;

import com.yuzarsif.api.client.football.model.HeadToHeadResponse;
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
public class HeadToHeadClient {

    private final RapidApiProperties rapidApiProperties;
    private final RestTemplate restTemplate;

    public HeadToHeadClient(RapidApiProperties rapidApiProperties, RestTemplate restTemplate) {
        this.rapidApiProperties = rapidApiProperties;
        this.restTemplate = restTemplate;
    }

    public HeadToHeadResponse findHeadToHead(String h2h) {
        String url = String.format("https://%s/fixtures/headtohead?h2h=%s&last=5", rapidApiProperties.getXRapidApiFootballHost(), h2h);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("x-rapidapi-host", rapidApiProperties.getXRapidApiFootballHost());
        headers.set("x-rapidapi-key", rapidApiProperties.getXRapidApiKey());

        HttpEntity entity = new HttpEntity(headers);

        try {
            ResponseEntity<HeadToHeadResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, HeadToHeadResponse.class);
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new EntityNotFoundException("Country not found");
    }
}
