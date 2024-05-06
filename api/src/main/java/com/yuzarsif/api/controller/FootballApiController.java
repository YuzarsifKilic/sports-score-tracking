package com.yuzarsif.api.controller;

import com.yuzarsif.api.client.football.*;
import com.yuzarsif.api.client.football.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/football-api")
public class FootballApiController {

    private final CountryClient countryClient;
    private final LeagueClient leagueClient;
    private final FixtureClient fixtureClient;
    private final HeadToHeadClient headToHeadClient;
    private final LineUpClient lineUpClient;
    private final StatisticsClient statisticsClient;
    private final TeamClient teamClient;

    public FootballApiController(CountryClient countryClient,
                                 LeagueClient leagueClient,
                                 FixtureClient fixtureClient,
                                 HeadToHeadClient headToHeadClient,
                                 LineUpClient lineUpClient,
                                 StatisticsClient statisticsClient,
                                 TeamClient teamClient) {
        this.countryClient = countryClient;
        this.leagueClient = leagueClient;
        this.fixtureClient = fixtureClient;
        this.headToHeadClient = headToHeadClient;
        this.lineUpClient = lineUpClient;
        this.statisticsClient = statisticsClient;
        this.teamClient = teamClient;
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

    @GetMapping("/lineups")
    public ResponseEntity<LineUpsResponse> getLineups(@RequestParam Integer fixtureId) {
        return ResponseEntity.ok(lineUpClient.findLineUp(fixtureId));
    }

    @GetMapping("/statistics")
    public ResponseEntity<StatisticResponse> getStatistics(@RequestParam Integer fixtureId) {
        return ResponseEntity.ok(statisticsClient.findStatistics(fixtureId));
    }

    @GetMapping("/leagues/teams")
    public ResponseEntity<List<TeamResponse.Response>> getTeams(@RequestParam Integer season, @RequestParam Integer leagueId) {
        return ResponseEntity.ok(teamClient.findTeams(season, leagueId));
    }
}
