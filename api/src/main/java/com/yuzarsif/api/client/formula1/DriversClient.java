package com.yuzarsif.api.client.formula1;

import com.yuzarsif.api.client.formula1.response.DriversResponse;
import com.yuzarsif.api.client.formula1.response.RankingResponse;
import com.yuzarsif.api.config.RapidApiProperties;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DriversClient {

    private final RapidApiProperties rapidApiProperties;
    private final RestTemplate restTemplate;

    public DriversClient(RapidApiProperties rapidApiProperties, RestTemplate restTemplate) {
        this.rapidApiProperties = rapidApiProperties;
        this.restTemplate = restTemplate;
    }

    public DriversResponse findDrivers(Optional<String> search, Optional<Integer> driverId) {
        String url = "";
        if (search.isPresent()) {
            url = String.format("https://%s/drivers?search=%s", rapidApiProperties.getXRapidApiFormula1Host(), search.get());
        } else if (driverId.isPresent()) {
            url = String.format("https://%s/drivers?id=%s", rapidApiProperties.getXRapidApiFormula1Host(), driverId.get());
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("x-rapidapi-host", rapidApiProperties.getXRapidApiFormula1Host());
        headers.set("x-rapidapi-key", rapidApiProperties.getXRapidApiKey());

        HttpEntity entity = new HttpEntity(headers);

        try {
            ResponseEntity<DriversResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, DriversResponse.class);
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new EntityNotFoundException("Country not found");
    }

}
