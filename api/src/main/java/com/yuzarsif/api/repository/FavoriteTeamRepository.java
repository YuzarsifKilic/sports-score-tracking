package com.yuzarsif.api.repository;

import com.yuzarsif.api.model.FavoriteTeam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteTeamRepository extends JpaRepository<FavoriteTeam, Long> {
}
