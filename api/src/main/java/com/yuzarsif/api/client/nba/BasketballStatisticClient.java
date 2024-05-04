package com.yuzarsif.api.client.nba;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuzarsif.api.client.nba.response.StatisticsResponse;
import com.yuzarsif.api.client.nba.response.TeamResponse;
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

import java.util.*;

@Service
public class BasketballStatisticClient {

    private final RapidApiProperties rapidApiProperties;
    private final RestTemplate restTemplate;
    private final ClientResponseService clientResponseService;

    public BasketballStatisticClient(RapidApiProperties rapidApiProperties, RestTemplate restTemplate, ClientResponseService clientResponseService) {
        this.rapidApiProperties = rapidApiProperties;
        this.restTemplate = restTemplate;
        this.clientResponseService = clientResponseService;
    }

    public StatisticsResponse findStatistics(Integer gameId) {
        String url = String.format("https://%s/players/statistics?game=%s", rapidApiProperties.getXRapidApiNbaHost(), gameId);

        Optional<ClientResponse> byRequest = clientResponseService.findByRequest(url);
        ObjectMapper objectMapper = new ObjectMapper();

        if (byRequest.isPresent()) {
            try {
                StatisticsResponse fixtureResponse = objectMapper.readValue(byRequest.get().getResponse(), StatisticsResponse.class);
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
            ResponseEntity<StatisticsResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, StatisticsResponse.class);
            clientResponseService.save(url, objectMapper.writeValueAsString(response.getBody()));
            return response.getBody();
        } catch (Exception e) {
            throw new ApiSportsException(String.format("Statics not found by game id: %s.\nError: %s", gameId, e.getMessage()));
        }
    }

    public StatisticsResponse.CustomResponse findStatistics(Integer playedId, Integer season) {
        String url = String.format("https://%s/players/statistics?id=%s&season=%s", rapidApiProperties.getXRapidApiNbaHost(), playedId, season);

        Optional<ClientResponse> byRequest = clientResponseService.findByRequest(url);
        ObjectMapper objectMapper = new ObjectMapper();

        if (byRequest.isPresent()) {
            try {
                StatisticsResponse.CustomResponse fixtureResponse = objectMapper.readValue(byRequest.get().getResponse(), StatisticsResponse.CustomResponse.class);
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
            ResponseEntity<StatisticsResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, StatisticsResponse.class);
            StatisticsResponse body = response.getBody();
            clientResponseService.save(url, objectMapper.writeValueAsString(body.getResponse()));
            return getYearStatistics(body.getResponse());
        } catch (Exception e) {
            throw new ApiSportsException(String.format("Statics not found by game id: %s.\nError: %s", playedId, e.getMessage()));
        }
    }

    private StatisticsResponse.CustomResponse getYearStatistics(List<StatisticsResponse.Response> responses) {
        StatisticsResponse.CustomResponse yearStatistic = StatisticsResponse.CustomResponse
                .builder()
                .player(responses.get(0).getPlayer())
                .team(responses.get(0).getTeam())
                .points(0)
                .min("00:00")
                .offReb(0)
                .defReb(0)
                .totReb(0)
                .blocks(0)
                .steals(0)
                .assists(0)
                .pos(new HashSet<>())
                .build();
        for (StatisticsResponse.Response response : responses) {
            if (response.getPoints() != null) {
                yearStatistic.setPoints(yearStatistic.getPoints() + response.getPoints());
            }
           if (response.getOffReb() != null) {
               yearStatistic.setOffReb(yearStatistic.getOffReb() + response.getOffReb());
           }
           if (response.getDefReb() != null) {
               yearStatistic.setDefReb(yearStatistic.getDefReb() + response.getDefReb());
           }
           if (response.getTotReb() != null) {
               yearStatistic.setTotReb(yearStatistic.getTotReb() + response.getTotReb());
           }
           if (response.getBlocks() != null) {
               yearStatistic.setBlocks(yearStatistic.getBlocks() + response.getBlocks());
           }
           if (response.getSteals() != null) {
               yearStatistic.setSteals(yearStatistic.getSteals() + response.getSteals());
           }
           if (response.getAssists() != null) {
               yearStatistic.setAssists(yearStatistic.getAssists() + response.getAssists());
           }
           if (response.getPos() != null) {
               yearStatistic.getPos().add(response.getPos());
           }
           if (response.getMin() != null && !response.getMin().equals("")) {
               String[] responseMin = response.getMin().split(":");
               int minutes = Integer.parseInt(responseMin[0]);
               int seconds = 0;
               if (responseMin.length > 1) seconds = Integer.parseInt(responseMin[1]);
               int sumMinutes = minutes + Integer.parseInt(yearStatistic.getMin().split(":")[0]);
               int sumSeconds = seconds + Integer.parseInt(yearStatistic.getMin().split(":")[1]);
               if (sumSeconds >= 60) {
                   sumMinutes += sumSeconds / 60;
                   sumSeconds = sumSeconds % 60;
               }
               yearStatistic.setMin(String.format("%02d:%02d", sumMinutes, sumSeconds));
           }
        }
        return yearStatistic;
    }
}
