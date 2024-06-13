package com.yuzarsif.api.repository;

import com.yuzarsif.api.model.FavoriteTeam;
import com.yuzarsif.api.model.SportFan;
import com.yuzarsif.api.model.SportType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface FavoriteTeamRepository extends JpaRepository<FavoriteTeam, Long> {

    Optional<FavoriteTeam> findByTeamIdAndSportType(Long teamId, SportType sportType);

    List<FavoriteTeam> findBySportFans(Set<SportFan> sportFans);

    List<FavoriteTeam> findBySportFansAndSportType(Set<SportFan> sportFans, SportType sportType);
}
