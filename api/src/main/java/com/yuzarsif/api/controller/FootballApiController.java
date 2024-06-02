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
    private final StandingsClient standingsClient;
    private final PlayersClient playersClient;

    public FootballApiController(CountryClient countryClient,
                                 LeagueClient leagueClient,
                                 FixtureClient fixtureClient,
                                 HeadToHeadClient headToHeadClient,
                                 LineUpClient lineUpClient,
                                 StatisticsClient statisticsClient,
                                 TeamClient teamClient,
                                 StandingsClient standingsClient,
                                 PlayersClient playersClient) {
        this.countryClient = countryClient;
        this.leagueClient = leagueClient;
        this.fixtureClient = fixtureClient;
        this.headToHeadClient = headToHeadClient;
        this.lineUpClient = lineUpClient;
        this.statisticsClient = statisticsClient;
        this.teamClient = teamClient;
        this.standingsClient = standingsClient;
        this.playersClient = playersClient;
    }

    @GetMapping("/leagues")
    public ResponseEntity<LeagueResponse> getLeagues(@RequestParam String code) {
        return ResponseEntity.ok(leagueClient.findLeagues(code));
    }

    @GetMapping("/fixtures")
    public ResponseEntity<List<FixtureCustomResponse.Response>> getFixtures(@RequestParam String date) {
        return ResponseEntity.ok(fixtureClient.findFixturesByDate(date));
    }

    @GetMapping("/teams")
    public ResponseEntity<TeamResponse.Response> getFixtures(@RequestParam Integer teamId) {
        return ResponseEntity.ok(teamClient.findTeamById(teamId));
    }

    @GetMapping("/fixtures/team")
    public ResponseEntity<List<FixtureCustomResponse.Response>> getFixturesByTeam(@RequestParam Integer teamId, @RequestParam Integer season) {
        return ResponseEntity.ok(fixtureClient.findFixtures(season, teamId));
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

    @GetMapping("/standings/team")
    public ResponseEntity<StandingsResponse> getStandingsByTeam(@RequestParam Integer season, @RequestParam Integer teamId) {
        return ResponseEntity.ok(standingsClient.findStandingsByTeamId(season, teamId));
    }

    @GetMapping("/standings")
    public ResponseEntity<List<StandingsResponse.Standings>> getStandings(@RequestParam Integer season, @RequestParam Integer leagueId) {
        return ResponseEntity.ok(standingsClient.findStandings(season, leagueId));
    }

    @GetMapping("/players")
    public ResponseEntity<PlayersResponse> getPlayers(@RequestParam Integer teamId, @RequestParam Integer season, @RequestParam(defaultValue = "1", required = false) Integer page) {
        return ResponseEntity.ok(playersClient.getPlayersByTeamAndSeason(teamId, season, page));
    }

    @GetMapping("/players/id")
    public ResponseEntity<PlayersCustomResponse> getPlayersById(@RequestParam Integer playerId, @RequestParam Integer season) {
        return ResponseEntity.ok(playersClient.getPlayersById(playerId, season));
    }

    @GetMapping("/matches/user")
    public ResponseEntity<FixtureResponse> getMatchesByUser(@RequestParam Long userId, @RequestParam String date) {
        return ResponseEntity.ok(fixtureClient.findFavoriteMatchesByUserId(date, userId));
    }
}
