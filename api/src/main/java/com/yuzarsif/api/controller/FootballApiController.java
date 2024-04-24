package com.yuzarsif.api.controller;

import com.yuzarsif.api.client.football.CountryClient;
import com.yuzarsif.api.client.football.LeagueClient;
import com.yuzarsif.api.client.football.model.CountryResponse;
import com.yuzarsif.api.client.football.model.LeagueResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/football-api")
public class FootballApiController {

    private final CountryClient countryClient;
    private final LeagueClient leagueClient;

    public FootballApiController(CountryClient countryClient, LeagueClient leagueClient) {
        this.countryClient = countryClient;
        this.leagueClient = leagueClient;
    }

    @GetMapping("/countries")
    public ResponseEntity<CountryResponse> getCountries(@RequestParam Optional<String> name) {
        return ResponseEntity.ok(countryClient.findCountries(name.orElse(null)));
    }

    @GetMapping("/leagues")
    public ResponseEntity<LeagueResponse> getLeagues(@RequestParam String code) {
        return ResponseEntity.ok(leagueClient.findLeagues(code));
    }
}
