package com.yuzarsif.api.client.formula1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuzarsif.api.client.formula1.response.DriversResponse;
import com.yuzarsif.api.client.formula1.response.FormulaTeamResponse;
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
public class DriversClient {

    private final RapidApiProperties rapidApiProperties;
    private final RestTemplate restTemplate;
    private final ClientResponseService clientResponseService;

    public DriversClient(RapidApiProperties rapidApiProperties, RestTemplate restTemplate, ClientResponseService clientResponseService) {
        this.rapidApiProperties = rapidApiProperties;
        this.restTemplate = restTemplate;
        this.clientResponseService = clientResponseService;
    }

    public DriversResponse findDrivers(Optional<String> search, Optional<Integer> driverId) {
        String url = "";
        if (search.isPresent()) {
            url = String.format("https://%s/drivers?search=%s", rapidApiProperties.getXRapidApiFormula1Host(), search.get());
        } else if (driverId.isPresent()) {
            url = String.format("https://%s/drivers?id=%s", rapidApiProperties.getXRapidApiFormula1Host(), driverId.get());
        }

        Optional<ClientResponse> byRequest = clientResponseService.findByRequest(url);
        ObjectMapper objectMapper = new ObjectMapper();

        if (byRequest.isPresent()) {
            try {
                DriversResponse fixtureResponse = objectMapper.readValue(byRequest.get().getResponse(), DriversResponse.class);
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
            ResponseEntity<DriversResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, DriversResponse.class);
            clientResponseService.save(url, objectMapper.writeValueAsString(response.getBody()));
            return response.getBody();
        } catch (Exception e) {
            throw new ApiSportsException(String.format("Driver not found by id %s.\nError: %s", url, e.getMessage()));
        }
    }

}
