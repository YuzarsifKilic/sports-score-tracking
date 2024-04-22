package com.yuzarsif.api.controller;

import com.yuzarsif.api.dto.CreateFootballFanRequest;
import com.yuzarsif.api.service.FootballFanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
