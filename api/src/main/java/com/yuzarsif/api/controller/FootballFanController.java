package com.yuzarsif.api.controller;

import com.yuzarsif.api.dto.CreateFootballFanRequest;
import com.yuzarsif.api.dto.FootballFanDto;
import com.yuzarsif.api.service.FootballFanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/football-fans")
public class FootballFanController {

    private final FootballFanService footballFanService;

    public FootballFanController(FootballFanService footballFanService) {
        this.footballFanService = footballFanService;
    }

    @PostMapping
    public ResponseEntity<Void> createFootballFan(@RequestBody CreateFootballFanRequest request) {
        footballFanService.createFootballFan(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FootballFanDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(footballFanService.getById(id));
    }
}
