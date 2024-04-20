package com.yuzarsif.api.service;

import com.yuzarsif.api.repository.FavoriteMatchRepository;
import org.springframework.stereotype.Service;

@Service
public class FavoriteMatchService {

    private final FavoriteMatchRepository favoriteMatchRepository;

    public FavoriteMatchService(FavoriteMatchRepository favoriteMatchRepository) {
        this.favoriteMatchRepository = favoriteMatchRepository;
    }
}
