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

    public Country findByName(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Country not found by name : " + name));
    }
}
