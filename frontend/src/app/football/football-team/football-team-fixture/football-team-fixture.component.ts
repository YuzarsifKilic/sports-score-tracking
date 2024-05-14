import { Component } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {FootballApiService} from "../../../_services/football-api.service";
import {FootballFixturesCustomResponse, FootballFixturesResponse, League} from "../../../_models/football-fixtures";

@Component({
  selector: 'app-football-team-fixture',
  templateUrl: './football-team-fixture.component.html',
  styleUrl: './football-team-fixture.component.css'
})
export class FootballTeamFixtureComponent {

  fixture!: FootballFixturesResponse[];
  leagues: League[] = [];
  selectedLeague!: League;
  seasons: string[] = []
  selectedSeason!: number;
  teamId!: number;

  constructor(private router: Router, private footballApiService: FootballApiService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    for (let i = 2000; i <= 2023; i++) {
      this.seasons.push(`${i}-${i + 1}`);
    }
    this.route.params.subscribe(params => {
      this.teamId = params['teamId'];
      this.footballApiService.getFixturesByTeam(params['teamId'], 2023)
        .then(response => {
          this.fixture = response;
          console.log(this.fixture);
          this.fixture.map(item => {
            this.leagues.push(item.league);
          })
          console.log(this.leagues);
        })
    })
  }

  onSelectLeague(selectedLeague: League) {

  }

  onSelectSeason(selectedSeason: number) {
    let season = selectedSeason.toString().split("-")[0];
    this.footballApiService.getFixturesByTeam(this.teamId, +season)
      .then(response => {
        this.fixture = response;
        console.log(this.fixture);
        this.fixture.map(item => {
          this.leagues.push(item.league);
        })
        console.log(this.leagues);
      })
  }
}
