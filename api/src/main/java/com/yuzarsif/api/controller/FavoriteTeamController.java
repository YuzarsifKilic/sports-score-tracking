package com.yuzarsif.api.controller;

import com.yuzarsif.api.dto.CreateFavoriteTeamRequest;
import com.yuzarsif.api.dto.DeleteFavoriteTeamRequest;
import com.yuzarsif.api.dto.FavoriteTeamDto;
import com.yuzarsif.api.service.FavoriteTeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/favorite-teams")
public class FavoriteTeamController {

    private final FavoriteTeamService favoriteTeamService;

    public FavoriteTeamController(FavoriteTeamService favoriteTeamService) {
        this.favoriteTeamService = favoriteTeamService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<FavoriteTeamDto>> getFavoriteTeamsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(favoriteTeamService.getFavoriteTeamsByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<Void> createFavoriteTeam(@RequestBody CreateFavoriteTeamRequest request, Authentication authentication) {
        favoriteTeamService.createFavoriteTeam(request, authentication);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteFavoriteTeam(@RequestBody DeleteFavoriteTeamRequest request, Authentication authentication) {
        favoriteTeamService.deleteFavoriteTeam(request, authentication);
        return ResponseEntity.ok().build();

    }
}
