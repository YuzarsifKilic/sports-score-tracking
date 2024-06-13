package com.yuzarsif.api.repository;

import com.yuzarsif.api.model.FavoriteMatch;
import com.yuzarsif.api.model.SportFan;
import com.yuzarsif.api.model.SportType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface FavoriteMatchRepository extends JpaRepository<FavoriteMatch, Long> {

    Optional<FavoriteMatch> findByMatchIdAndSportType(Long matchId, SportType sportType);

    List<FavoriteMatch> findBySportFans(Set<SportFan> sportFans);

    List<FavoriteMatch> findBySportFansAndSportType(Set<SportFan> sportFans, SportType sportType);
}
