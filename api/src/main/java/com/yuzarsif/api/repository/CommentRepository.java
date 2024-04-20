package com.yuzarsif.api.repository;

import com.yuzarsif.api.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
