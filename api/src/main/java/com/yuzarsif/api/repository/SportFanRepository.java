package com.yuzarsif.api.repository;

import com.yuzarsif.api.model.SportFan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SportFanRepository extends JpaRepository<SportFan, Long> {

    Optional<SportFan> findByEmail(String email);

}
