package com.yuzarsif.api.client.football;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuzarsif.api.client.football.model.FixtureCustomResponse;
import com.yuzarsif.api.client.football.model.LineUpsResponse;
import com.yuzarsif.api.client.football.model.StandingsResponse;
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
public class StandingsClient {

    private final RapidApiProperties rapidApiProperties;
    private final RestTemplate restTemplate;
    private final ClientResponseService clientResponseService;

    public StandingsClient(RapidApiProperties rapidApiProperties, RestTemplate restTemplate, ClientResponseService clientResponseService) {
        this.rapidApiProperties = rapidApiProperties;
        this.restTemplate = restTemplate;
        this.clientResponseService = clientResponseService;
    }

    public List<StandingsResponse.Standings> findStandings(Integer season, Integer leagueId) {
        String url = String.format("https://%s/standings?season=%s&league=%s", rapidApiProperties.getXRapidApiFootballHost(), season, leagueId);

        Optional<ClientResponse> byRequest = clientResponseService.findByRequest(url);
        ObjectMapper objectMapper = new ObjectMapper();

        if (byRequest.isPresent()) {
            try {
                List<StandingsResponse.Standings> standingsResponse = objectMapper.readValue(byRequest.get().getResponse(), new TypeReference<List<StandingsResponse.Standings>>(){});
                return standingsResponse;
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
            ResponseEntity<StandingsResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, StandingsResponse.class);
            clientResponseService.save(url, objectMapper.writeValueAsString(response.getBody().getResponse().get(0).getLeague().getStandings().get(0)));
            return response.getBody().getResponse().get(0).getLeague().getStandings().get(0);
        } catch (Exception e) {
            throw new ApiSportsException(String.format("Standings not found by season %s and league %s\nError: %s", season, leagueId, e.getMessage()));
        }
    }

    public StandingsResponse findStandingsByTeamId(Integer season, Integer teamId) {
        String url = String.format("https://%s/standings?season=%s&team=%s", rapidApiProperties.getXRapidApiFootballHost(), season, teamId);

        Optional<ClientResponse> byRequest = clientResponseService.findByRequest(url);
        ObjectMapper objectMapper = new ObjectMapper();

        if (byRequest.isPresent()) {
            try {
                StandingsResponse standingsResponse = objectMapper.readValue(byRequest.get().getResponse(), StandingsResponse.class);
                return standingsResponse;
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
            ResponseEntity<StandingsResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, StandingsResponse.class);
            clientResponseService.save(url, objectMapper.writeValueAsString(response.getBody()));
            return response.getBody();
        } catch (Exception e) {
            throw new ApiSportsException(String.format("Standings not found by season %s and teamId %s\nError: %s", season, teamId, e.getMessage()));
        }
    }
}
