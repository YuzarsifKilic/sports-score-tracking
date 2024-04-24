package com.yuzarsif.api.controller;

import com.yuzarsif.api.client.nba.BasketballStatisticClient;
import com.yuzarsif.api.client.nba.GameClient;
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

    public NbaApiController(GameClient gameClient, BasketballStatisticClient basketballStatisticClient) {
        this.gameClient = gameClient;
        this.basketballStatisticClient = basketballStatisticClient;
    }

    @GetMapping("/games")
    public ResponseEntity<GameResponse> getGames(@RequestParam(required = false) Optional<String> date, @RequestParam(required = false) Optional<String> matchId) {
        return ResponseEntity.ok(gameClient.findGames(date, matchId));
    }

    @GetMapping("/games/statistics")
    public ResponseEntity<StatisticsResponse> getStatistics(@RequestParam Integer matchId) {
        return ResponseEntity.ok(basketballStatisticClient.findStatistics(matchId));
    }
}
