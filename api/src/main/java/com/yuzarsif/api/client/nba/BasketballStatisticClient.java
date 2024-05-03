package com.yuzarsif.api.client.nba;

import com.yuzarsif.api.client.nba.response.StatisticsResponse;
import com.yuzarsif.api.config.RapidApiProperties;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BasketballStatisticClient {

    private final RapidApiProperties rapidApiProperties;
    private final RestTemplate restTemplate;

    public BasketballStatisticClient(RapidApiProperties rapidApiProperties, RestTemplate restTemplate) {
        this.rapidApiProperties = rapidApiProperties;
        this.restTemplate = restTemplate;
    }

    public StatisticsResponse findStatistics(Integer gameId) {
        String url = String.format("https://%s/players/statistics?game=%s", rapidApiProperties.getXRapidApiNbaHost(), gameId);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("x-rapidapi-host", rapidApiProperties.getXRapidApiNbaHost());
        headers.set("x-rapidapi-key", rapidApiProperties.getXRapidApiKey());

        HttpEntity entity = new HttpEntity(headers);

        try {
            ResponseEntity<StatisticsResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, StatisticsResponse.class);
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new EntityNotFoundException("Country not found");
    }

    public StatisticsResponse.CustomResponse findStatistics(Integer playedId, Integer season) {
        String url = String.format("https://%s/players/statistics?id=%s&season=%s", rapidApiProperties.getXRapidApiNbaHost(), playedId, season);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("x-rapidapi-host", rapidApiProperties.getXRapidApiNbaHost());
        headers.set("x-rapidapi-key", rapidApiProperties.getXRapidApiKey());

        HttpEntity entity = new HttpEntity(headers);

        try {
            ResponseEntity<StatisticsResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, StatisticsResponse.class);
            StatisticsResponse body = response.getBody();
            return getYearStatistics(body.getResponse());
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new EntityNotFoundException("Country not found");
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
