package com.yuzarsif.api.service;

import com.yuzarsif.api.dto.CreateFavoriteMatchRequest;
import com.yuzarsif.api.model.FavoriteMatch;
import com.yuzarsif.api.model.FootballFan;
import com.yuzarsif.api.repository.FavoriteMatchRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FavoriteMatchService {

    private final FavoriteMatchRepository favoriteMatchRepository;
    private final FootballFanService footballFanService;

    public FavoriteMatchService(FavoriteMatchRepository favoriteMatchRepository, FootballFanService footballFanService) {
        this.favoriteMatchRepository = favoriteMatchRepository;
        this.footballFanService = footballFanService;
    }

    public void createFavoriteMatch(CreateFavoriteMatchRequest request) {
        FootballFan footballFan = footballFanService.findById(request.userId());

        Optional<FavoriteMatch> byMatchIdAndSportType = favoriteMatchRepository.findByMatchIdAndSportType(request.matchId(), request.sportType());
        if (byMatchIdAndSportType.isPresent()) {
            byMatchIdAndSportType.get().getFootballFans().add(footballFan);
            favoriteMatchRepository.save(byMatchIdAndSportType.get());
            return;
        }

        FavoriteMatch favoriteMatch = FavoriteMatch
                .builder()
                .matchId(request.matchId())
                .sportType(request.sportType())
                .build();

        FavoriteMatch savedMatch = favoriteMatchRepository.save(favoriteMatch);
        savedMatch.getFootballFans().add(footballFan);

        favoriteMatchRepository.save(savedMatch);
    }
}
