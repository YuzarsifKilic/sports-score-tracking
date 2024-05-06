package com.yuzarsif.api.controller;

import com.yuzarsif.api.model.Country;
import com.yuzarsif.api.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Country>> findAll() {
        return ResponseEntity.ok(countryService.findAll());
    }

    @GetMapping
    public Country findByName(@RequestParam String code) {
        return countryService.findByCode(code);
    }
}
