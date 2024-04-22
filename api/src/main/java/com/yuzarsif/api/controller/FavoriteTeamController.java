package com.yuzarsif.api.controller;

import com.yuzarsif.api.dto.CreateFavoriteTeamRequest;
import com.yuzarsif.api.service.FavoriteTeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/favorite-teams")
public class FavoriteTeamController {

    private final FavoriteTeamService favoriteTeamService;

    public FavoriteTeamController(FavoriteTeamService favoriteTeamService) {
        this.favoriteTeamService = favoriteTeamService;
    }

    @PostMapping
    public ResponseEntity<Void> createFavoriteTeam(@RequestBody CreateFavoriteTeamRequest request) {
        favoriteTeamService.createFavoriteTeam(request);
        return ResponseEntity.ok().build();
    }
}
