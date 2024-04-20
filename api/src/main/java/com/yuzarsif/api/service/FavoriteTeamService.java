package com.yuzarsif.api.service;

import com.yuzarsif.api.repository.FavoriteTeamRepository;
import org.springframework.stereotype.Service;

@Service
public class FavoriteTeamService {

    private final FavoriteTeamRepository favoriteTeamRepository;

    public FavoriteTeamService(FavoriteTeamRepository favoriteTeamRepository) {
        this.favoriteTeamRepository = favoriteTeamRepository;
    }
}
