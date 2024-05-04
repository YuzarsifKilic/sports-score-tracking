package com.yuzarsif.api.client.nba;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuzarsif.api.client.football.model.StandingsResponse;
import com.yuzarsif.api.client.nba.response.GameResponse;
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
public class GameClient {

    private final RapidApiProperties rapidApiProperties;
    private final RestTemplate restTemplate;
    private final ClientResponseService clientResponseService;

    public GameClient(RapidApiProperties rapidApiProperties, RestTemplate restTemplate, ClientResponseService clientResponseService) {
        this.rapidApiProperties = rapidApiProperties;
        this.restTemplate = restTemplate;
        this.clientResponseService = clientResponseService;
    }

    public GameResponse findGames(Optional<String> date, Optional<String> matchId, Optional<String> h2h) {
        String url = "";
        if (date.isPresent()) {
            url = String.format("https://%s/games?date=%s", rapidApiProperties.getXRapidApiNbaHost(), date.get());
        } else if (matchId.isPresent()) {
            url = String.format("https://%s/games?id=%s", rapidApiProperties.getXRapidApiNbaHost(), matchId.get());
        } else if (h2h.isPresent()) {
            url = String.format("https://%s/games?h2h=%s", rapidApiProperties.getXRapidApiNbaHost(), h2h.get());
        }

        Optional<ClientResponse> byRequest = clientResponseService.findByRequest(url);
        ObjectMapper objectMapper = new ObjectMapper();

        if (byRequest.isPresent()) {
            try {
                GameResponse fixtureResponse = objectMapper.readValue(byRequest.get().getResponse(), GameResponse.class);
                return fixtureResponse;
            } catch (JsonProcessingException e) {
                throw new EntityNotFoundException("Country not found");
            }
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("x-rapidapi-host", rapidApiProperties.getXRapidApiNbaHost());
        headers.set("x-rapidapi-key", rapidApiProperties.getXRapidApiKey());

        HttpEntity entity = new HttpEntity(headers);

        try {
            ResponseEntity<GameResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, GameResponse.class);
            clientResponseService.save(url, objectMapper.writeValueAsString(response.getBody()));
            return response.getBody();
        } catch (Exception e) {
            throw new ApiSportsException(String.format("Games not found. Error: %s", e.getMessage()));
        }
    }
}
