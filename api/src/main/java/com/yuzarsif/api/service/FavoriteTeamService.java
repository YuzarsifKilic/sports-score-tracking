package com.yuzarsif.api.service;

import com.yuzarsif.api.dto.CreateFavoriteTeamRequest;
import com.yuzarsif.api.model.FavoriteTeam;
import com.yuzarsif.api.model.FootballFan;
import com.yuzarsif.api.repository.FavoriteTeamRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class FavoriteTeamService {

    private final FavoriteTeamRepository favoriteTeamRepository;
    private final FootballFanService footballFanService;

    public FavoriteTeamService(FavoriteTeamRepository favoriteTeamRepository, FootballFanService footballFanService) {
        this.favoriteTeamRepository = favoriteTeamRepository;
        this.footballFanService = footballFanService;
    }

    public void createFavoriteTeam(CreateFavoriteTeamRequest request) {
        FootballFan footballFan = footballFanService.findById(request.userId());

        Optional<FavoriteTeam> byTeamIdAndSportType = favoriteTeamRepository.findByTeamIdAndSportType(request.teamId(), request.sportType());
        if (byTeamIdAndSportType.isPresent()) {
            byTeamIdAndSportType.get().getFootballFans().add(footballFan);
            favoriteTeamRepository.save(byTeamIdAndSportType.get());
            return;
        }
        FavoriteTeam favoriteTeam = FavoriteTeam
                .builder()
                .teamId(request.teamId())
                .sportType(request.sportType())
                .build();

        FavoriteTeam savedFavoriteTeam = favoriteTeamRepository.save(favoriteTeam);
        savedFavoriteTeam.getFootballFans().add(footballFan);
        favoriteTeamRepository.save(savedFavoriteTeam);
    }
}
