package com.yuzarsif.api.client.football;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuzarsif.api.client.football.model.LeagueResponse;
import com.yuzarsif.api.client.football.model.LineUpsResponse;
import com.yuzarsif.api.config.RapidApiProperties;
import com.yuzarsif.api.exception.ApiSportsException;
import com.yuzarsif.api.model.ClientResponse;
import com.yuzarsif.api.service.ClientResponseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class LineUpClient {

    private final RapidApiProperties rapidApiProperties;
    private final RestTemplate restTemplate;
    private final ClientResponseService clientResponseService;

    public LineUpClient(RapidApiProperties rapidApiProperties, RestTemplate restTemplate, ClientResponseService clientResponseService) {
        this.rapidApiProperties = rapidApiProperties;
        this.restTemplate = restTemplate;
        this.clientResponseService = clientResponseService;
    }

    public LineUpsResponse findLineUp(Integer fixtureId) {
        String url = String.format("https://%s/fixtures/lineups?fixture=%s", rapidApiProperties.getXRapidApiFootballHost(), fixtureId);

        Optional<ClientResponse> byRequest = clientResponseService.findByRequest(url);
        ObjectMapper objectMapper = new ObjectMapper();

        if (byRequest.isPresent()) {
            try {
                LineUpsResponse fixtureResponse = objectMapper.readValue(byRequest.get().getResponse(), LineUpsResponse.class);
                return fixtureResponse;
            } catch (JsonProcessingException e) {
                throw new EntityNotFoundException("Country not found");
            }
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("x-rapidapi-host", rapidApiProperties.getXRapidApiFootballHost());
        headers.set("x-rapidapi-key", rapidApiProperties.getXRapidApiKey());

        HttpEntity entity = new HttpEntity(headers);

        try {
            ResponseEntity<LineUpsResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, LineUpsResponse.class);
            clientResponseService.save(url, objectMapper.writeValueAsString(response.getBody()));
            return response.getBody();
        } catch (Exception e) {
            throw new ApiSportsException(String.format("LineUp not found by fixture id %s\nError: %s", fixtureId, e.getMessage()));
        }
    }
}
