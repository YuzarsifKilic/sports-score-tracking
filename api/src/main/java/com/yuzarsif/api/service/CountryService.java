package com.yuzarsif.api.service;

import com.yuzarsif.api.model.Country;
import com.yuzarsif.api.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    private final CountryRepository repository;

    public CountryService(CountryRepository repository) {
        this.repository = repository;
    }

    public List<Country> findAll() {
        return repository.findAll();
    }

    public Country findByCode(String code) {
        return repository.findByName(code)
                .orElseThrow(() -> new IllegalArgumentException("Country not found by name : " + code));
    }
}
