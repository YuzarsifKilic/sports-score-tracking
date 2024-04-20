package com.yuzarsif.api.repository;

import com.yuzarsif.api.model.BaseUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BaseUserRepository extends JpaRepository<BaseUser, Long> {

    Optional<BaseUser> findByEmail(String email);
}
