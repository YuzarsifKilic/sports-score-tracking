package com.yuzarsif.api.controller;

import com.yuzarsif.api.client.nba.*;
import com.yuzarsif.api.client.nba.response.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/nba-api")
public class NbaApiController {

    private final GameClient gameClient;
    private final BasketballStatisticClient basketballStatisticClient;
    private final BasketballPlayerClient basketballPlayerClient;
    private final BasketballTeamClient basketballTeamClient;
    private final BasketballStandingsClient basketballStandingsClient;

    public NbaApiController(GameClient gameClient, BasketballStatisticClient basketballStatisticClient, BasketballPlayerClient basketballPlayerClient, BasketballTeamClient basketballTeamClient, BasketballStandingsClient basketballStandingsClient) {
        this.gameClient = gameClient;
        this.basketballStatisticClient = basketballStatisticClient;
        this.basketballPlayerClient = basketballPlayerClient;
        this.basketballTeamClient = basketballTeamClient;
        this.basketballStandingsClient = basketballStandingsClient;
    }

    @GetMapping("/games")
    public ResponseEntity<GameResponse> getGames(@RequestParam(required = false) Optional<String> date, @RequestParam(required = false) Optional<String> matchId, @RequestParam(required = false) Optional<String> h2h) {
        return ResponseEntity.ok(gameClient.findGames(date, matchId, h2h));
    }

    @GetMapping("/games/statistics")
    public ResponseEntity<StatisticsResponse> getStatistics(@RequestParam Integer matchId) {
        return ResponseEntity.ok(basketballStatisticClient.findStatistics(matchId));
    }

    @GetMapping("/players")
    public ResponseEntity<BasketballPlayerResponse.Response> getPlayer(@RequestParam Integer playerId) {
        return ResponseEntity.ok(basketballPlayerClient.findBasketballPlayer(playerId));
    }

    @GetMapping("/players/statistics")
    public ResponseEntity<StatisticsResponse.CustomResponse> getStatistics(@RequestParam Integer playerId, @RequestParam Integer season) {
        return ResponseEntity.ok(basketballStatisticClient.findStatistics(playerId, season));
    }

    @GetMapping("/teams")
    public ResponseEntity<TeamResponse> getTeamById(@RequestParam Long teamId) {
        return ResponseEntity.ok(basketballTeamClient.findTeamById(teamId));
    }

    @GetMapping("/standings")
    public ResponseEntity<StandingsResponse> getStandings(@RequestParam Integer season, @RequestParam String league) {
        return ResponseEntity.ok(basketballStandingsClient.findStandings(season, league));
    }

    @GetMapping("/team/players")
    public ResponseEntity<BasketballPlayerResponse> getPlayersByTeam(@RequestParam Integer teamId, @RequestParam Integer season) {
        return ResponseEntity.ok(basketballPlayerClient.findBasketballPlayerByTeam(teamId, season));
    }

    @GetMapping("/team/games")
    public ResponseEntity<GameResponse> getGamesByTeam(@RequestParam Integer teamId, @RequestParam Integer season) {
        return ResponseEntity.ok(gameClient.findGamesByTeam(teamId, season));
    }

    @GetMapping("/games/user")
    public ResponseEntity<GameResponse> getFavoriteGames(@RequestParam Long userId, @RequestParam String date) {
        return ResponseEntity.ok(gameClient.findFavoriteMatches(date, userId));
    }
}
