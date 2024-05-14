package com.yuzarsif.api.client.football;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuzarsif.api.client.football.model.HeadToHeadResponse;
import com.yuzarsif.api.client.football.model.PlayersCustomResponse;
import com.yuzarsif.api.client.football.model.PlayersResponse;
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
public class PlayersClient {

    private final RapidApiProperties rapidApiProperties;
    private final RestTemplate restTemplate;
    private final ClientResponseService clientResponseService;

    public PlayersClient(RapidApiProperties rapidApiProperties, RestTemplate restTemplate, ClientResponseService clientResponseService) {
        this.rapidApiProperties = rapidApiProperties;
        this.restTemplate = restTemplate;
        this.clientResponseService = clientResponseService;
    }

    public PlayersResponse getPlayersByTeamAndSeason(Integer teamId, Integer season, Integer page) {
        String url = String.format("https://%s/players?team=%s&season=%s&page=%s", rapidApiProperties.getXRapidApiFootballHost(), teamId, season, page);

        Optional<ClientResponse> byRequest = clientResponseService.findByRequest(url);
        ObjectMapper objectMapper = new ObjectMapper();

        if (byRequest.isPresent()) {
            try {
                PlayersResponse headToHeadResponse = objectMapper.readValue(byRequest.get().getResponse(), PlayersResponse.class);
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
            ResponseEntity<PlayersResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, PlayersResponse.class);
            clientResponseService.save(url, objectMapper.writeValueAsString(response.getBody()));
            return response.getBody();
        } catch (Exception e) {
            throw new ApiSportsException(String.format("Players not found by team id %s and season %s\nError: %s", teamId, season, e.getMessage()));
        }
    }

    public PlayersCustomResponse getPlayersById(Integer playerId, Integer season) {
        String url = String.format("https://%s/players?id=%s&season=%s", rapidApiProperties.getXRapidApiFootballHost(), playerId, season);

        Optional<ClientResponse> byRequest = clientResponseService.findByRequest(url);
        ObjectMapper objectMapper = new ObjectMapper();

        if (byRequest.isPresent()) {
            try {
                PlayersCustomResponse headToHeadResponse = objectMapper.readValue(byRequest.get().getResponse(), PlayersCustomResponse.class);
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
            ResponseEntity<PlayersCustomResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, PlayersCustomResponse.class);
            clientResponseService.save(url, objectMapper.writeValueAsString(response.getBody()));
            return response.getBody();
        } catch (Exception e) {
            throw new ApiSportsException(String.format("Players not found by id %s and season %s\nError: %s", playerId, season, e.getMessage()));
        }
    }
}
