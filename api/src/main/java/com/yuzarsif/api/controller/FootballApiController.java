package com.yuzarsif.api.controller;

import com.yuzarsif.api.client.football.CountryClient;
import com.yuzarsif.api.client.football.FixtureClient;
import com.yuzarsif.api.client.football.HeadToHeadClient;
import com.yuzarsif.api.client.football.LeagueClient;
import com.yuzarsif.api.client.football.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/football-api")
public class FootballApiController {

    private final CountryClient countryClient;
    private final LeagueClient leagueClient;
    private final FixtureClient fixtureClient;
    private final HeadToHeadClient headToHeadClient;

    public FootballApiController(CountryClient countryClient, LeagueClient leagueClient, FixtureClient fixtureClient, HeadToHeadClient headToHeadClient) {
        this.countryClient = countryClient;
        this.leagueClient = leagueClient;
        this.fixtureClient = fixtureClient;
        this.headToHeadClient = headToHeadClient;
    }

    @GetMapping("/countries")
    public ResponseEntity<CountryResponse> getCountries(@RequestParam Optional<String> name) {
        return ResponseEntity.ok(countryClient.findCountries(name.orElse(null)));
    }

    @GetMapping("/leagues")
    public ResponseEntity<LeagueResponse> getLeagues(@RequestParam String code) {
        return ResponseEntity.ok(leagueClient.findLeagues(code));
    }

    @GetMapping("/fixtures")
    public ResponseEntity<List<FixtureCustomResponse.Response>> getFixtures(@RequestParam String date) {
        return ResponseEntity.ok(fixtureClient.findFixturesByDate(date));
    }

    @GetMapping("/fixtures/{fixtureId}")
    public ResponseEntity<FixtureResponse.Response> getFixturesById(@PathVariable Integer fixtureId) {
        return ResponseEntity.ok(fixtureClient.findFixturesById(fixtureId));
    }

    @GetMapping("/last-fixtures/{teamId}")
    public ResponseEntity<List<FixtureResponse.Response>> get5FixturesByTeamId(@PathVariable Integer teamId) {
        return ResponseEntity.ok(fixtureClient.find5FixturesByTeamId(teamId));
    }

    @GetMapping("/head-to-head")
    public ResponseEntity<HeadToHeadResponse> getHeadToHead(@RequestParam String h2h) {
        return ResponseEntity.ok(headToHeadClient.findHeadToHead(h2h));
    }
}
