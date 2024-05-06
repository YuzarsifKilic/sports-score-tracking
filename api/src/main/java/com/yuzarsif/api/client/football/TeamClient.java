package com.yuzarsif.api.client.football;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuzarsif.api.client.football.model.FixtureCustomResponse;
import com.yuzarsif.api.client.football.model.FixtureResponse;
import com.yuzarsif.api.client.football.model.TeamResponse;
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
public class TeamClient {

    private final RapidApiProperties rapidApiProperties;
    private final RestTemplate restTemplate;
    private final ClientResponseService clientResponseService;

    public TeamClient(RapidApiProperties rapidApiProperties, RestTemplate restTemplate, ClientResponseService clientResponseService) {
        this.rapidApiProperties = rapidApiProperties;
        this.restTemplate = restTemplate;
        this.clientResponseService = clientResponseService;
    }

    public List<TeamResponse.Response> findTeams(Integer season, Integer leagueId) {
        String url = String.format("https://%s/teams?season=%s&league=%s", rapidApiProperties.getXRapidApiFootballHost(), season, leagueId);

        Optional<ClientResponse> byRequest = clientResponseService.findByRequest(url);
        ObjectMapper objectMapper = new ObjectMapper();

        if (byRequest.isPresent()) {
            try {
                List<TeamResponse.Response> fixtureResponse = objectMapper.readValue(byRequest.get().getResponse(), new TypeReference<List<TeamResponse.Response>>(){});
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
            ResponseEntity<TeamResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, TeamResponse.class);
            clientResponseService.save(url, objectMapper.writeValueAsString(response.getBody().getResponse()));
            return response.getBody().getResponse();
        } catch (Exception e) {
            throw new ApiSportsException(String.format("Team not found by season %s and league id %s\nError: %s", season, leagueId, e.getMessage()));
        }
    }
}
