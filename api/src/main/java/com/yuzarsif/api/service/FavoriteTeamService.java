package com.yuzarsif.api.service;

import com.yuzarsif.api.dto.CreateFavoriteTeamRequest;
import com.yuzarsif.api.dto.DeleteFavoriteTeamRequest;
import com.yuzarsif.api.dto.FavoriteTeamDto;
import com.yuzarsif.api.model.FavoriteTeam;
import com.yuzarsif.api.model.SportFan;
import com.yuzarsif.api.model.SportType;
import com.yuzarsif.api.repository.FavoriteTeamRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FavoriteTeamService {

    private final FavoriteTeamRepository favoriteTeamRepository;
    private final SportFanService sportFanService;

    public FavoriteTeamService(FavoriteTeamRepository favoriteTeamRepository, SportFanService sportFanService) {
        this.favoriteTeamRepository = favoriteTeamRepository;
        this.sportFanService = sportFanService;
    }

    public void createFavoriteTeam(CreateFavoriteTeamRequest request) {
        SportFan sportFan = sportFanService.findById(request.userId());

        Optional<FavoriteTeam> byTeamIdAndSportType = favoriteTeamRepository.findByTeamIdAndSportType(request.teamId(), request.sportType());
        if (byTeamIdAndSportType.isPresent()) {
            if (byTeamIdAndSportType.get().getSportFans().isEmpty()) {
                Set<SportFan> sportFans = new HashSet<>();
                sportFans.add(sportFan);
                byTeamIdAndSportType.get().setSportFans(sportFans);
                favoriteTeamRepository.save(byTeamIdAndSportType.get());
            } else {
                byTeamIdAndSportType.get().getSportFans().add(sportFan);
                favoriteTeamRepository.save(byTeamIdAndSportType.get());
            }
        } else {
            FavoriteTeam favoriteTeam = FavoriteTeam
                    .builder()
                    .teamId(request.teamId())
                    .sportType(request.sportType())
                    .sportFans(Set.of(sportFan))
                    .build();

            favoriteTeamRepository.save(favoriteTeam);
        }
    }

    public List<FavoriteTeamDto> getFavoriteTeamsByUserIdAndSportType(Long userId, SportType sportType) {
        return favoriteTeamRepository.findBySportFansAndSportType(Set.of(sportFanService.findById(userId)), sportType)
                .stream()
                .map(FavoriteTeamDto::convert)
                .collect(Collectors.toList());
    }

    public List<FavoriteTeamDto> getFavoriteTeamsByUserId(Long userId) {
        return favoriteTeamRepository.findBySportFans(Set.of(sportFanService.findById(userId)))
                .stream()
                .map(FavoriteTeamDto::convert)
                .collect(Collectors.toList());
    }

    public void deleteFavoriteTeam(DeleteFavoriteTeamRequest request) {
        Optional<FavoriteTeam> byTeamIdAndSportType = favoriteTeamRepository.findByTeamIdAndSportType(request.teamId(), request.sportType());
        if (byTeamIdAndSportType.isPresent()) {
            byTeamIdAndSportType.get().getSportFans().remove(sportFanService.findById(request.userId()));
            favoriteTeamRepository.save(byTeamIdAndSportType.get());
        }
    }
}
