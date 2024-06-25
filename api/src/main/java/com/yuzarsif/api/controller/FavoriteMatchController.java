package com.yuzarsif.api.controller;

import com.yuzarsif.api.dto.FavoriteMatchRequest;
import com.yuzarsif.api.service.FavoriteMatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/favorite-matches")
public class FavoriteMatchController {

    private final FavoriteMatchService favoriteMatchService;

    public FavoriteMatchController(FavoriteMatchService favoriteMatchService) {
        this.favoriteMatchService = favoriteMatchService;
    }

    @PostMapping
    public ResponseEntity<Void> createFavoriteMatch(@RequestBody FavoriteMatchRequest request, Authentication authentication) {
        favoriteMatchService.createFavoriteMatch(request, authentication);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/check")
    public ResponseEntity<Boolean> checkFavoriteMatch(@RequestBody FavoriteMatchRequest request, Authentication authentication) {
        return ResponseEntity.ok(favoriteMatchService.checkFavoriteMatch(request, authentication));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteFavoriteMatch(@RequestBody FavoriteMatchRequest request, Authentication authentication) {
        favoriteMatchService.deleteFavoriteMatch(request, authentication);
        return ResponseEntity.ok().build();
    }
}
