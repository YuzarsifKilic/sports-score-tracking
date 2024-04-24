package com.yuzarsif.api.client.football;

import com.yuzarsif.api.client.football.model.FixtureResponse;
import com.yuzarsif.api.client.football.model.StatisticResponse;
import com.yuzarsif.api.client.nba.response.StatisticsResponse;
import com.yuzarsif.api.config.RapidApiProperties;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StatisticsClient {

    private final RapidApiProperties rapidApiProperties;
    private final RestTemplate restTemplate;

    public StatisticsClient(RapidApiProperties rapidApiProperties, RestTemplate restTemplate) {
        this.rapidApiProperties = rapidApiProperties;
        this.restTemplate = restTemplate;
    }

    public StatisticsResponse findStatistics(Integer fixtureId) {
        String url = String.format("https://%s/fixtures/statistics?fixture=%s", rapidApiProperties.getXRapidApiFootballHost(), fixtureId);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("x-rapidapi-host", rapidApiProperties.getXRapidApiFootballHost());
        headers.set("x-rapidapi-key", rapidApiProperties.getXRapidApiKey());

        HttpEntity entity = new HttpEntity(headers);

        try {
            ResponseEntity<StatisticsResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, StatisticsResponse.class);
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new EntityNotFoundException("Country not found");
    }
}
