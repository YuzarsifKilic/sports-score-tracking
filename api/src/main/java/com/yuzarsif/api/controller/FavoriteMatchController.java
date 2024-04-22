package com.yuzarsif.api.controller;

import com.yuzarsif.api.dto.CreateFavoriteMatchRequest;
import com.yuzarsif.api.service.FavoriteMatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/favorite-matches")
public class FavoriteMatchController {

    private final FavoriteMatchService favoriteMatchService;

    public FavoriteMatchController(FavoriteMatchService favoriteMatchService) {
        this.favoriteMatchService = favoriteMatchService;
    }

    @PostMapping
    public ResponseEntity<Void> createFavoriteMatch(@RequestBody CreateFavoriteMatchRequest request) {
        favoriteMatchService.createFavoriteMatch(request);
        return ResponseEntity.ok().build();
    }
}
