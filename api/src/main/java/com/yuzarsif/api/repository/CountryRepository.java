package com.yuzarsif.api.repository;

import com.yuzarsif.api.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Integer> {

    Optional<Country> findByName(String name);
    Optional<Country> findByCode(String code);
}
