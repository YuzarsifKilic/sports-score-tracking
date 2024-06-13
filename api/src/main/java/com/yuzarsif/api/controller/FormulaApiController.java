package com.yuzarsif.api.controller;

import com.yuzarsif.api.client.formula1.DriversClient;
import com.yuzarsif.api.client.formula1.FormulaTeamClient;
import com.yuzarsif.api.client.formula1.RacesClient;
import com.yuzarsif.api.client.formula1.RankingClient;
import com.yuzarsif.api.client.formula1.response.DriversResponse;
import com.yuzarsif.api.client.formula1.response.FormulaTeamResponse;
import com.yuzarsif.api.client.formula1.response.RaceResponse;
import com.yuzarsif.api.client.formula1.response.RankingResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/formula-api")
public class FormulaApiController {

    private final RacesClient racesClient;
    private final RankingClient rankingClient;
    private final DriversClient driversClient;
    private final FormulaTeamClient formulaTeamClient;

    public FormulaApiController(RacesClient racesClient, RankingClient rankingClient, DriversClient driversClient, FormulaTeamClient formulaTeamClient) {
        this.racesClient = racesClient;
        this.rankingClient = rankingClient;
        this.driversClient = driversClient;
        this.formulaTeamClient = formulaTeamClient;
    }

    @GetMapping("/races")
    public ResponseEntity<RaceResponse> getRaces(@RequestParam(defaultValue = "2024") Integer season, @RequestParam String type) {
        return ResponseEntity.ok(racesClient.findRaces(season, type));
    }

    @GetMapping("/rankings/{raceId}")
    public ResponseEntity<RankingResponse> getRankings(@PathVariable Integer raceId) {
        return ResponseEntity.ok(rankingClient.findRanking(raceId));
    }

    @GetMapping("/teams")
    public ResponseEntity<FormulaTeamResponse.Response> getTeamById(@RequestParam Integer teamId) {
        return ResponseEntity.ok(formulaTeamClient.findRaces(teamId));
    }

    @GetMapping("/drivers")
    public ResponseEntity<DriversResponse> getDrivers(@RequestParam Optional<String> name, @RequestParam Optional<Integer> driverId) {
        return ResponseEntity.ok(driversClient.findDrivers(name, driverId));
    }
}
