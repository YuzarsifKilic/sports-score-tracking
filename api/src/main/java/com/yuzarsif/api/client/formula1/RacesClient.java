package com.yuzarsif.api.client.formula1;

import com.yuzarsif.api.client.formula1.response.RaceResponse;
import com.yuzarsif.api.client.nba.response.StandingsResponse;
import com.yuzarsif.api.config.RapidApiProperties;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RacesClient {

    private final RapidApiProperties rapidApiProperties;
    private final RestTemplate restTemplate;

    public RacesClient(RapidApiProperties rapidApiProperties, RestTemplate restTemplate) {
        this.rapidApiProperties = rapidApiProperties;
        this.restTemplate = restTemplate;
    }

    public RaceResponse findRaces(Integer season) {
        String url = String.format("https://%s/races?season=%s", rapidApiProperties.getXRapidApiFormula1Host(), season);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("x-rapidapi-host", rapidApiProperties.getXRapidApiFormula1Host());
        headers.set("x-rapidapi-key", rapidApiProperties.getXRapidApiKey());

        HttpEntity entity = new HttpEntity(headers);

        try {
            ResponseEntity<RaceResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, RaceResponse.class);
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new EntityNotFoundException("Country not found");
    }
}
