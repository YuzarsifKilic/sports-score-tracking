package com.yuzarsif.api.client.nba;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuzarsif.api.client.nba.response.BasketballPlayerResponse;
import com.yuzarsif.api.client.nba.response.StandingsResponse;
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
public class BasketballPlayerClient {

    private final RapidApiProperties rapidApiProperties;
    private final RestTemplate restTemplate;
    private final ClientResponseService clientResponseService;

    public BasketballPlayerClient(RapidApiProperties rapidApiProperties, RestTemplate restTemplate, ClientResponseService clientResponseService) {
        this.rapidApiProperties = rapidApiProperties;
        this.restTemplate = restTemplate;
        this.clientResponseService = clientResponseService;
    }

    public BasketballPlayerResponse.Response findBasketballPlayer(Integer id) {
        String url = String.format("https://%s/players?id=%s", rapidApiProperties.getXRapidApiNbaHost(), id);

        Optional<ClientResponse> byRequest = clientResponseService.findByRequest(url);
        ObjectMapper objectMapper = new ObjectMapper();

        if (byRequest.isPresent()) {
            try {
                BasketballPlayerResponse.Response fixtureResponse = objectMapper.readValue(byRequest.get().getResponse(), BasketballPlayerResponse.Response.class);
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
            ResponseEntity<BasketballPlayerResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, BasketballPlayerResponse.class);
            clientResponseService.save(url, objectMapper.writeValueAsString(response.getBody().getResponse().get(0)));
            return response.getBody().getResponse().get(0);
        } catch (Exception e) {
            throw new ApiSportsException(String.format("Basketball player not found by id %s.\nError: %s", id, e.getMessage()));
        }
    }
}
