package com.yuzarsif.api;

import com.yuzarsif.api.client.football.CountryClient;
import com.yuzarsif.api.client.football.LeagueClient;
import com.yuzarsif.api.client.football.StandingsClient;
import com.yuzarsif.api.client.football.TeamClient;
import com.yuzarsif.api.client.football.model.CountryResponse;
import com.yuzarsif.api.client.football.model.LeagueResponse;
import com.yuzarsif.api.client.football.model.StandingsResponse;
import com.yuzarsif.api.client.football.model.TeamResponse;
import com.yuzarsif.api.dto.CreateFavoriteTeamRequest;
import com.yuzarsif.api.model.FavoriteTeam;
import com.yuzarsif.api.model.FootballFan;
import com.yuzarsif.api.model.Role;
import com.yuzarsif.api.model.SportType;
import com.yuzarsif.api.repository.FavoriteTeamRepository;
import com.yuzarsif.api.repository.FootballFanRepository;
import com.yuzarsif.api.service.FavoriteTeamService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
                                               StandingsClient standingsClient) {
        return args -> {
        };
    }

}
