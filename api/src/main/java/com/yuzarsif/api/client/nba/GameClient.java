package com.yuzarsif.api.client.nba;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuzarsif.api.client.nba.response.GameResponse;
import com.yuzarsif.api.config.RapidApiProperties;
import com.yuzarsif.api.dto.FavoriteTeamDto;
import com.yuzarsif.api.exception.ApiSportsException;
import com.yuzarsif.api.model.ClientResponse;
import com.yuzarsif.api.model.SportType;
import com.yuzarsif.api.service.ClientResponseService;
import com.yuzarsif.api.service.FavoriteTeamService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameClient {

    private final RapidApiProperties rapidApiProperties;
    private final RestTemplate restTemplate;
    private final ClientResponseService clientResponseService;
    private final FavoriteTeamService favoriteTeamService;

    public GameClient(RapidApiProperties rapidApiProperties, RestTemplate restTemplate, ClientResponseService clientResponseService, FavoriteTeamService favoriteTeamService) {
        this.rapidApiProperties = rapidApiProperties;
        this.restTemplate = restTemplate;
        this.clientResponseService = clientResponseService;
        this.favoriteTeamService = favoriteTeamService;
    }

    public GameResponse findFavoriteMatches(String date, Long userId) {
        String url  =String.format("https://%s/games?date=%s", rapidApiProperties.getXRapidApiNbaHost(), date);

        List<FavoriteTeamDto> favoriteTeamsByUserIdAndSportType = favoriteTeamService.getFavoriteTeamsByUserIdAndSportType(userId, SportType.BASKETBALL);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("x-rapidapi-host", rapidApiProperties.getXRapidApiNbaHost());
        headers.set("x-rapidapi-key", rapidApiProperties.getXRapidApiKey());

        HttpEntity entity = new HttpEntity(headers);

        try {
            ResponseEntity<GameResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, GameResponse.class);
            ArrayList<GameResponse.Response> responseList = new ArrayList<>();
            for (GameResponse.Response res: response.getBody().response) {
                for (FavoriteTeamDto favoriteTeamDto: favoriteTeamsByUserIdAndSportType) {
                    if (res.getTeams().getHome().getId() == favoriteTeamDto.teamId() || res.getTeams().getVisitors().getId() == favoriteTeamDto.teamId()) {
                        responseList.add(res);
                    }
                }
            }
            GameResponse favorites = new GameResponse();
            favorites.setResponse(responseList);
            return favorites;
        } catch (Exception e) {
            throw new ApiSportsException(String.format("Games not found. Error: %s", e.getMessage()));
        }
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

    public GameResponse findGamesByTeam(Integer teamId, Integer season) {
        String url = String.format("https://%s/games?team=%s&season=%s", rapidApiProperties.getXRapidApiNbaHost(), teamId, season);

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
