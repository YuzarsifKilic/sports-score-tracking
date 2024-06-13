package com.yuzarsif.api.controller;

import com.yuzarsif.api.dto.CreateFootballFanRequest;
import com.yuzarsif.api.dto.FootballFanDto;
import com.yuzarsif.api.service.SportFanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/football-fans")
public class FootballFanController {

    private final SportFanService sportFanService;

    public FootballFanController(SportFanService sportFanService) {
        this.sportFanService = sportFanService;
    }

    @PostMapping
    public ResponseEntity<Void> createFootballFan(@RequestBody CreateFootballFanRequest request) {
        sportFanService.createFootballFan(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FootballFanDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(sportFanService.getById(id));
    }
}
