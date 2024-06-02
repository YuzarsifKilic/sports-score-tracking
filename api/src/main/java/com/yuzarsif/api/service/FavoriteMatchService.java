package com.yuzarsif.api.service;

import com.yuzarsif.api.dto.FavoriteMatchRequest;
import com.yuzarsif.api.model.FavoriteMatch;
import com.yuzarsif.api.model.SportFan;
import com.yuzarsif.api.repository.FavoriteMatchRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FavoriteMatchService {

    private final FavoriteMatchRepository favoriteMatchRepository;
    private final SportFanService sportFanService;

    public FavoriteMatchService(FavoriteMatchRepository favoriteMatchRepository, SportFanService sportFanService) {
        this.favoriteMatchRepository = favoriteMatchRepository;
        this.sportFanService = sportFanService;
    }

    public void createFavoriteMatch(FavoriteMatchRequest request) {
        SportFan sportFan = sportFanService.findById(request.userId());

        Optional<FavoriteMatch> byMatchIdAndSportType = favoriteMatchRepository.findByMatchIdAndSportType(request.matchId(), request.sportType());
        if (byMatchIdAndSportType.isPresent()) {
            if (byMatchIdAndSportType.get().getSportFans().isEmpty()) {
                Set<SportFan> sportFans = new HashSet<>();
                sportFans.add(sportFan);
                byMatchIdAndSportType.get().setSportFans(sportFans);
                favoriteMatchRepository.save(byMatchIdAndSportType.get());
            } else {
                byMatchIdAndSportType.get().getSportFans().add(sportFan);
                favoriteMatchRepository.save(byMatchIdAndSportType.get());
            }
        }

        FavoriteMatch favoriteMatch = FavoriteMatch
                .builder()
                .matchId(request.matchId())
                .sportType(request.sportType())
                .sportFans(Set.of(sportFan))
                .build();

        favoriteMatchRepository.save(favoriteMatch);
    }

    public Boolean checkFavoriteMatch(FavoriteMatchRequest request) {
        SportFan sportFan = sportFanService.findById(request.userId());
        List<FavoriteMatch> favoriteMatches = favoriteMatchRepository.findBySportFans(Set.of(sportFan));
        for (FavoriteMatch favoriteMatch : favoriteMatches) {
            if (favoriteMatch.getMatchId().equals(request.matchId()) && favoriteMatch.getSportType().equals(request.sportType())) {
                return true;
            }
        }
        return false;
    }

    public void deleteFavoriteMatch(FavoriteMatchRequest request) {
        Optional<FavoriteMatch> byMatchIdAndSportType = favoriteMatchRepository.findByMatchIdAndSportType(request.matchId(), request.sportType());
        if (byMatchIdAndSportType.isPresent()) {
            byMatchIdAndSportType.get().getSportFans().remove(sportFanService.findById(request.userId()));
            favoriteMatchRepository.save(byMatchIdAndSportType.get());
        }
    }
}
