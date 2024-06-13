import { Component } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {NbaApiService} from "../../../_services/nba-api.service";
import {NbaGames} from "../../../_models/nba-games";

@Component({
  selector: 'app-basketball-team-games',
  templateUrl: './basketball-team-games.component.html',
  styleUrl: './basketball-team-games.component.css'
})
export class BasketballTeamGamesComponent {

  games!: NbaGames;
  years: number[] = [2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023];
  selectedYear: number = 2023;
  teamId!: number;

  constructor(private router: Router, private nbaApiService: NbaApiService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.teamId = params['teamId'];
      this.nbaApiService.getMatchesByTeam(params['teamId'], 2023)
        .then(response => {
          this.games = response;
        })
    })
  }

  onSelectSeason(selectedSeason: number) {
    this.nbaApiService.getMatchesByTeam(this.teamId, selectedSeason)
      .then(response => {
        this.games = response;
      })
  }

  protected readonly parseInt = parseInt;
}
