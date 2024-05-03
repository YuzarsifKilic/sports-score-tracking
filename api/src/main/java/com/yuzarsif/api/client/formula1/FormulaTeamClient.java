package com.yuzarsif.api.client.formula1;

import com.yuzarsif.api.client.formula1.response.FormulaTeamResponse;
import com.yuzarsif.api.client.formula1.response.RaceResponse;
import com.yuzarsif.api.config.RapidApiProperties;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FormulaTeamClient {

    private final RapidApiProperties rapidApiProperties;
    private final RestTemplate restTemplate;

    public FormulaTeamClient(RapidApiProperties rapidApiProperties, RestTemplate restTemplate) {
        this.rapidApiProperties = rapidApiProperties;
        this.restTemplate = restTemplate;
    }

    public FormulaTeamResponse.Response findRaces(Integer id) {
        String url = String.format("https://%s/teams?id=%s", rapidApiProperties.getXRapidApiFormula1Host(), id);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("x-rapidapi-host", rapidApiProperties.getXRapidApiFormula1Host());
        headers.set("x-rapidapi-key", rapidApiProperties.getXRapidApiKey());

        HttpEntity entity = new HttpEntity(headers);

        try {
            ResponseEntity<FormulaTeamResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, FormulaTeamResponse.class);
            return response.getBody().getResponse().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new EntityNotFoundException("Country not found");
    }
}
