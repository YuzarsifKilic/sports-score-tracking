package com.yuzarsif.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuzarsif.api.client.football.*;
import com.yuzarsif.api.client.football.model.*;
import com.yuzarsif.api.dto.CreateFavoriteTeamRequest;
import com.yuzarsif.api.model.*;
import com.yuzarsif.api.repository.CountryRepository;
import com.yuzarsif.api.repository.FavoriteTeamRepository;
import com.yuzarsif.api.repository.FootballFanRepository;
import com.yuzarsif.api.service.FavoriteTeamService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.*;

@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(FootballFanRepository footballFanRepository,
                                               FavoriteTeamRepository favoriteTeamRepository,
                                               FavoriteTeamService favoriteTeamService,
                                               CountryClient countryClient,
                                               LeagueClient leagueClient,
                                               TeamClient teamClient,
                                               StandingsClient standingsClient,
                                               FixtureClient fixtureClient,
                                               CountryRepository repository) {
        return args -> {

        };
    }

}
