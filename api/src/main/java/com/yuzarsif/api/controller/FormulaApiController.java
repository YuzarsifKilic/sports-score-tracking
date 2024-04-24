package com.yuzarsif.api.controller;

import com.yuzarsif.api.client.formula1.RacesClient;
import com.yuzarsif.api.client.formula1.RankingClient;
import com.yuzarsif.api.client.formula1.response.RaceResponse;
import com.yuzarsif.api.client.formula1.response.RankingResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/formula-api")
public class FormulaApiController {

    private final RacesClient racesClient;
    private final RankingClient rankingClient;

    public FormulaApiController(RacesClient racesClient, RankingClient rankingClient) {
        this.racesClient = racesClient;
        this.rankingClient = rankingClient;
    }

    @GetMapping("/races")
    public ResponseEntity<RaceResponse> getRaces(@RequestParam(defaultValue = "2024") Integer season) {
        return ResponseEntity.ok(racesClient.findRaces(season));
    }

    @GetMapping("/rankings/{raceId}")
    public ResponseEntity<RankingResponse> getRankings(@PathVariable Integer raceId) {
        return ResponseEntity.ok(rankingClient.findRanking(raceId));
    }
}
