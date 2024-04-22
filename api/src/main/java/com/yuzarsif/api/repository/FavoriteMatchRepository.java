package com.yuzarsif.api.repository;

import com.yuzarsif.api.model.FavoriteMatch;
import com.yuzarsif.api.model.SportType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavoriteMatchRepository extends JpaRepository<FavoriteMatch, Long> {

    Optional<FavoriteMatch> findByMatchIdAndSportType(Long matchId, SportType sportType);
}
