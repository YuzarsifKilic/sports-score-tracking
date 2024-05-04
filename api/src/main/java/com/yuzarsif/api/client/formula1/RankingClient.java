package com.yuzarsif.api.client.formula1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuzarsif.api.client.formula1.response.RankingResponse;
import com.yuzarsif.api.client.nba.response.LeagueResponse;
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
public class RankingClient {

    private final RapidApiProperties rapidApiProperties;
    private final RestTemplate restTemplate;
    private final ClientResponseService clientResponseService;

    public RankingClient(RapidApiProperties rapidApiProperties, RestTemplate restTemplate, ClientResponseService clientResponseService) {
        this.rapidApiProperties = rapidApiProperties;
        this.restTemplate = restTemplate;
        this.clientResponseService = clientResponseService;
    }

    public RankingResponse findRanking(Integer raceId) {
        String url = String.format("https://%s/rankings/races?race=%s", rapidApiProperties.getXRapidApiFormula1Host(), raceId);

        Optional<ClientResponse> byRequest = clientResponseService.findByRequest(url);
        ObjectMapper objectMapper = new ObjectMapper();

        if (byRequest.isPresent()) {
            try {
                RankingResponse fixtureResponse = objectMapper.readValue(byRequest.get().getResponse(), RankingResponse.class);
                return fixtureResponse;
            } catch (JsonProcessingException e) {
                throw new EntityNotFoundException("Country not found");
            }
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("x-rapidapi-host", rapidApiProperties.getXRapidApiFormula1Host());
        headers.set("x-rapidapi-key", rapidApiProperties.getXRapidApiKey());

        HttpEntity entity = new HttpEntity(headers);

        try {
            ResponseEntity<RankingResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, RankingResponse.class);
            clientResponseService.save(url, objectMapper.writeValueAsString(response.getBody()));
            return response.getBody();
        } catch (Exception e) {
            throw new ApiSportsException(String.format("Rankings not found by id %s.\nError: %s", raceId, e.getMessage()));
        }
    }
}
