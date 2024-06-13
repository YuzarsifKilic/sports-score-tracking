package com.yuzarsif.api.client.football;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuzarsif.api.client.football.model.FixtureCustomResponse;
import com.yuzarsif.api.client.football.model.FixtureResponse;
import com.yuzarsif.api.config.RapidApiProperties;
import com.yuzarsif.api.dto.FavoriteMatchDto;
import com.yuzarsif.api.dto.FavoriteMatchRequest;
import com.yuzarsif.api.dto.FavoriteTeamDto;
import com.yuzarsif.api.exception.ApiSportsException;
import com.yuzarsif.api.model.ClientResponse;
import com.yuzarsif.api.model.SportType;
import com.yuzarsif.api.service.ClientResponseService;
import com.yuzarsif.api.service.FavoriteMatchService;
import com.yuzarsif.api.service.FavoriteTeamService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class FixtureClient {

    private final RapidApiProperties rapidApiProperties;
    private final RestTemplate restTemplate;
    private final ClientResponseService clientResponseService;
    private final FavoriteTeamService favoriteTeamService;
    private final FavoriteMatchService favoriteMatchService;

    public FixtureClient(RapidApiProperties rapidApiProperties, RestTemplate restTemplate, ClientResponseService clientResponseService, FavoriteTeamService favoriteTeamService, FavoriteMatchService favoriteMatchService) {
        this.rapidApiProperties = rapidApiProperties;
        this.restTemplate = restTemplate;
        this.clientResponseService = clientResponseService;
        this.favoriteTeamService = favoriteTeamService;
        this.favoriteMatchService = favoriteMatchService;
    }

    public List<FixtureCustomResponse.Response> findFixtures(Integer season, Integer teamId) {
        String url = String.format("https://%s/fixtures?season=%s&team=%s", rapidApiProperties.getXRapidApiFootballHost(), season, teamId);

        Optional<ClientResponse> byRequest = clientResponseService.findByRequest(url);
        ObjectMapper objectMapper = new ObjectMapper();

        if (byRequest.isPresent()) {
            try {
                ArrayList<FixtureCustomResponse.Response> fixtureResponse = objectMapper.readValue(byRequest.get().getResponse(), new TypeReference<ArrayList<FixtureCustomResponse.Response>>(){});
                return fixtureResponse;
            } catch (JsonProcessingException e) {
                throw new EntityNotFoundException("Country not found");
            }
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("x-rapidapi-host", rapidApiProperties.getXRapidApiFootballHost());
        headers.set("x-rapidapi-key", rapidApiProperties.getXRapidApiKey());

        HttpEntity entity = new HttpEntity(headers);

        try {
            ResponseEntity<FixtureResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, FixtureResponse.class);
            List<FixtureCustomResponse.Response> customResponse = convertToCustomResponse(response.getBody().getResponse());
            clientResponseService.save(url, objectMapper.writeValueAsString(customResponse));
            return customResponse;
        } catch (Exception e) {
            throw new ApiSportsException(String.format("Fixture not found by season %s and team id %s\nError: %s", season, teamId, e.getMessage()));
        }
    }

    public FixtureResponse.Response findFixturesById(Integer fixtureId) {
        String url = String.format("https://%s/fixtures?id=%s", rapidApiProperties.getXRapidApiFootballHost(), fixtureId);

        Optional<ClientResponse> byRequest = clientResponseService.findByRequest(url);
        ObjectMapper objectMapper = new ObjectMapper();

        if (byRequest.isPresent()) {
            try {
                FixtureResponse.Response fixtureResponse = objectMapper.readValue(byRequest.get().getResponse(), FixtureResponse.Response.class);
                return fixtureResponse;
            } catch (JsonProcessingException e) {
                throw new EntityNotFoundException("Country not found");
            }
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("x-rapidapi-host", rapidApiProperties.getXRapidApiFootballHost());
        headers.set("x-rapidapi-key", rapidApiProperties.getXRapidApiKey());

        HttpEntity entity = new HttpEntity(headers);

        try {
            ResponseEntity<FixtureResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, FixtureResponse.class);
            FixtureResponse body = response.getBody();
            FixtureResponse.Response fixtureResponse = body.getResponse().get(0);
            clientResponseService.save(url, objectMapper.writeValueAsString(fixtureResponse));
            return fixtureResponse;
        } catch (Exception e) {
            throw new ApiSportsException(String.format("Fixture not found by id %s\nError: %s", fixtureId, e.getMessage()));
        }
    }

    public List<FixtureResponse.Response> find5FixturesByTeamId(Integer teamId) {
        String url = String.format("https://%s/fixtures?team=%s&last=5", rapidApiProperties.getXRapidApiFootballHost(), teamId);

        Optional<ClientResponse> byRequest = clientResponseService.findByRequest(url);

        ObjectMapper objectMapper = new ObjectMapper();

        if (byRequest.isPresent()) {
            try {
                List<FixtureResponse.Response> fixtureResponse = objectMapper.readValue(byRequest.get().getResponse(), new TypeReference<List<FixtureResponse.Response>>(){});
                return fixtureResponse;
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
            ResponseEntity<FixtureResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, FixtureResponse.class);
            FixtureResponse body = response.getBody();
            assert body != null;
            clientResponseService.save(url, objectMapper.writeValueAsString(body.getResponse()));
            return body.getResponse();
        } catch (Exception e) {
            throw new ApiSportsException(String.format("Fixtures not found by team id %s\nError: %s", teamId, e.getMessage()));
        }
    }

    public FixtureResponse findFavoriteMatchesByUserId(String date, Long userId) {
        String url = String.format("https://%s/fixtures?date=%s", rapidApiProperties.getXRapidApiFootballHost(), date);
        List<FavoriteTeamDto> favoriteTeamsByUserId = favoriteTeamService.getFavoriteTeamsByUserId(userId);
        List<FavoriteMatchDto> favoriteMatchesByUserIdAndSportType = favoriteMatchService.getFavoriteMatchesByUserIdAndSportType(userId, SportType.FOOTBALL);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("x-rapidapi-host", rapidApiProperties.getXRapidApiFootballHost());
        headers.set("x-rapidapi-key", rapidApiProperties.getXRapidApiKey());

        HttpEntity entity = new HttpEntity(headers);

        try {
            ResponseEntity<FixtureResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, FixtureResponse.class);
            FixtureResponse body = response.getBody();
            ArrayList<FixtureResponse.Response> favoriteMatches = new ArrayList<>();
            for (FixtureResponse.Response fixture : body.getResponse()) {
                for (FavoriteTeamDto favoriteTeam : favoriteTeamsByUserId) {
                    if (favoriteTeam.teamId().equals((long) fixture.teams.home.id) || favoriteTeam.teamId().equals((long) fixture.teams.away.id)) {
                        favoriteMatches.add(fixture);
                    }
                }
                for (FavoriteMatchDto favoriteMatch : favoriteMatchesByUserIdAndSportType) {
                    if (favoriteMatch.matchId().equals((long) fixture.fixture.id) && !favoriteMatches.contains(fixture)) {
                        favoriteMatches.add(fixture);
                    }
                }
            }
            FixtureResponse response1 = new FixtureResponse();
            response1.setResponse(favoriteMatches);
            return response1;
        } catch (Exception e) {
            throw new ApiSportsException(String.format("Fixtures not found by date %s\nError: %s", date, e.getMessage()));
        }
    }

    public List<FixtureCustomResponse.Response> findFixturesByDate(String date) {
        String url = String.format("https://%s/fixtures?date=%s", rapidApiProperties.getXRapidApiFootballHost(), date);

        Optional<ClientResponse> byRequest = clientResponseService.findByRequest(url);

        ObjectMapper objectMapper = new ObjectMapper();

        if (byRequest.isPresent()) {
            try {
                List<FixtureCustomResponse.Response> fixtureResponse = objectMapper.readValue(byRequest.get().getResponse(), new TypeReference<List<FixtureCustomResponse.Response>>(){});
                return fixtureResponse;
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
            ResponseEntity<FixtureResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, FixtureResponse.class);
            FixtureResponse body = response.getBody();
            List<FixtureCustomResponse.Response> responses = convertToCustomResponse(body.getResponse());
            int order = 0;
            if (changeOrder(order, "Turkey", "Süper Lig", responses)){
                order++;
            }
            if (changeOrder(order, "England", "Premier League", responses)){
                order++;
            }
            if (changeOrder(order, "Italy", "Serie A", responses)){
                order++;
            }
            if (changeOrder(order, "Germany", "Bundesliga", responses)){
                order++;
            }
            if (changeOrder(order, "Spain", "La Liga", responses)){
                order++;
            }
            clientResponseService.save(url, objectMapper.writeValueAsString(responses));
            return responses;
        } catch (Exception e) {
            throw new ApiSportsException(String.format("Fixtures not found by date %s\nError: %s", date, e.getMessage()));
        }
    }

    private List<FixtureCustomResponse.Response> convertToCustomResponse(List<FixtureResponse.Response> fixtureResponses) {
        HashSet<String> leagues = new HashSet<>();
        List<FixtureCustomResponse.Response> responseList = new ArrayList<>();

        for (FixtureResponse.Response fixtureResponse: fixtureResponses) {
            if (!leagues.contains(fixtureResponse.getLeague().getName() + " " + fixtureResponse.getLeague().getCountry())) {
                leagues.add(fixtureResponse.getLeague().getName() + " " + fixtureResponse.getLeague().getCountry());
                FixtureCustomResponse.Response response2 = FixtureCustomResponse.Response.builder().build();
                response2.setLeague(fixtureResponse.getLeague());
                FixtureCustomResponse.CustomResponse customResponse = FixtureCustomResponse.CustomResponse
                        .builder()
                        .score(fixtureResponse.getScore())
                        .fixture(fixtureResponse.getFixture())
                        .goals(fixtureResponse.getGoals())
                        .teams(fixtureResponse.getTeams())
                        .build();
                response2.setResponse(new ArrayList<>());
                response2.getResponse().add(customResponse);
                responseList.add(response2);
            } else {
                for (FixtureCustomResponse.Response response2 : responseList) {
                    if (response2.getLeague().getName().equals(fixtureResponse.getLeague().getName()) && response2.getLeague().getCountry().equals(fixtureResponse.getLeague().getCountry())) {
                        FixtureCustomResponse.CustomResponse customResponse = FixtureCustomResponse.CustomResponse
                                .builder()
                                .score(fixtureResponse.getScore())
                                .fixture(fixtureResponse.getFixture())
                                .goals(fixtureResponse.getGoals())
                                .teams(fixtureResponse.getTeams())
                                .build();
                        response2.getResponse().add(customResponse);
                    }
                }
            }
        }
        return responseList;
    }

    private boolean changeOrder(int number, String countryName, String leagueName, List<FixtureCustomResponse.Response> response) {
        for (int i = 0; i < response.size(); i++) {
            if (response.get(i).getLeague().getCountry().equals(countryName) && response.get(i).getLeague().getName().equals(leagueName)) {
                Collections.swap(response, i, number);
                return true;
            }
        }
        return false;
    }
}
