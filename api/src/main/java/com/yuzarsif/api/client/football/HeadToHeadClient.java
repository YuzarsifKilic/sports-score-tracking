package com.yuzarsif.api.client.football;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuzarsif.api.client.football.model.FixtureResponse;
import com.yuzarsif.api.client.football.model.HeadToHeadResponse;
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

import java.util.List;
import java.util.Optional;

@Service
public class HeadToHeadClient {

    private final RapidApiProperties rapidApiProperties;
    private final RestTemplate restTemplate;
    private final ClientResponseService clientResponseService;

    public HeadToHeadClient(RapidApiProperties rapidApiProperties, RestTemplate restTemplate, ClientResponseService clientResponseService) {
        this.rapidApiProperties = rapidApiProperties;
        this.restTemplate = restTemplate;
        this.clientResponseService = clientResponseService;
    }

    public HeadToHeadResponse findHeadToHead(String h2h) {
        String url = String.format("https://%s/fixtures/headtohead?h2h=%s&last=5", rapidApiProperties.getXRapidApiFootballHost(), h2h);

        Optional<ClientResponse> byRequest = clientResponseService.findByRequest(url);
        ObjectMapper objectMapper = new ObjectMapper();

        if (byRequest.isPresent()) {
            try {
                HeadToHeadResponse headToHeadResponse = objectMapper.readValue(byRequest.get().getResponse(), HeadToHeadResponse.class);
                return headToHeadResponse;
            } catch (JsonProcessingException e) {
                throw new EntityNotFoundException(e.getMessage());
            }
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("x-rapidapi-host", rapidApiProperties.getXRapidApiFootballHost());
        headers.set("x-rapidapi-key", rapidApiProperties.getXRapidApiKey());

        HttpEntity entity = new HttpEntity(headers);

        try {
            ResponseEntity<HeadToHeadResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, HeadToHeadResponse.class);
            clientResponseService.save(url, objectMapper.writeValueAsString(response.getBody()));
            return response.getBody();
        } catch (Exception e) {
            throw new ApiSportsException(String.format("Head to head not found by h2h %s\nError: %s", h2h, e.getMessage()));
        }
    }
}
