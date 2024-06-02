package com.yuzarsif.api;

import com.yuzarsif.api.client.football.*;
import com.yuzarsif.api.repository.CountryRepository;
import com.yuzarsif.api.repository.FavoriteTeamRepository;
import com.yuzarsif.api.repository.SportFanRepository;
import com.yuzarsif.api.service.FavoriteTeamService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(SportFanRepository sportFanRepository,
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
