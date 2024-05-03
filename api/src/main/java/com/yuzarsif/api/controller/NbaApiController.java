package com.yuzarsif.api.controller;

import com.yuzarsif.api.client.nba.BasketballPlayerClient;
import com.yuzarsif.api.client.nba.BasketballStatisticClient;
import com.yuzarsif.api.client.nba.GameClient;
import com.yuzarsif.api.client.nba.response.BasketballPlayerResponse;
import com.yuzarsif.api.client.nba.response.GameResponse;
import com.yuzarsif.api.client.nba.response.StatisticsResponse;
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

    public NbaApiController(GameClient gameClient, BasketballStatisticClient basketballStatisticClient, BasketballPlayerClient basketballPlayerClient) {
        this.gameClient = gameClient;
        this.basketballStatisticClient = basketballStatisticClient;
        this.basketballPlayerClient = basketballPlayerClient;
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
}
