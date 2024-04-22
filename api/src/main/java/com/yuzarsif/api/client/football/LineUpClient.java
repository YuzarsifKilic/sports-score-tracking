package com.yuzarsif.api.client.football;

import com.yuzarsif.api.client.football.model.HeadToHeadResponse;
import com.yuzarsif.api.client.football.model.LineUpsResponse;
import com.yuzarsif.api.config.RapidApiProperties;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LineUpClient {

    private final RapidApiProperties rapidApiProperties;
    private final RestTemplate restTemplate;

    public LineUpClient(RapidApiProperties rapidApiProperties, RestTemplate restTemplate) {
        this.rapidApiProperties = rapidApiProperties;
        this.restTemplate = restTemplate;
    }

    public LineUpsResponse findLineUp(Integer fixtureId) {
        String url = String.format("https://%s/fixtures/lineups?fixture=%s", rapidApiProperties.getXRapidApiFootballHost(), fixtureId);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("x-rapidapi-host", rapidApiProperties.getXRapidApiFootballHost());
        headers.set("x-rapidapi-key", rapidApiProperties.getXRapidApiKey());

        HttpEntity entity = new HttpEntity(headers);

        try {
            ResponseEntity<LineUpsResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, LineUpsResponse.class);
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new EntityNotFoundException("Country not found");
    }
}
