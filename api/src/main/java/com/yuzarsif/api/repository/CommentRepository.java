package com.yuzarsif.api.repository;

import com.yuzarsif.api.model.Comment;
import com.yuzarsif.api.model.SportType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByMatchIdAndSportType(Long matchId, SportType sportType);
}
