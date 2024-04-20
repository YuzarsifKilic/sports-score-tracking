package com.yuzarsif.api.repository;

import com.yuzarsif.api.model.FavoriteMatch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteMatchRepository extends JpaRepository<FavoriteMatch, Long> {
}
