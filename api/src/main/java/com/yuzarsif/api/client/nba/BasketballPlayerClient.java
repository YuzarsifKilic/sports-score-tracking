package com.yuzarsif.api.client.nba;

import com.yuzarsif.api.client.nba.response.BasketballPlayerResponse;
import com.yuzarsif.api.client.nba.response.StatisticsResponse;
import com.yuzarsif.api.config.RapidApiProperties;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BasketballPlayerClient {

    private final RapidApiProperties rapidApiProperties;
    private final RestTemplate restTemplate;

    public BasketballPlayerClient(RapidApiProperties rapidApiProperties, RestTemplate restTemplate) {
        this.rapidApiProperties = rapidApiProperties;
        this.restTemplate = restTemplate;
    }

    public BasketballPlayerResponse.Response findBasketballPlayer(Integer id) {
        String url = String.format("https://%s/players?id=%s", rapidApiProperties.getXRapidApiNbaHost(), id);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("x-rapidapi-host", rapidApiProperties.getXRapidApiNbaHost());
        headers.set("x-rapidapi-key", rapidApiProperties.getXRapidApiKey());

        HttpEntity entity = new HttpEntity(headers);

        try {
            ResponseEntity<BasketballPlayerResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, BasketballPlayerResponse.class);
            return response.getBody().getResponse().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new EntityNotFoundException("Country not found");
    }
}
