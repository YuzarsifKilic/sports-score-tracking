import { Component } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {FootballApiService} from "../../_services/football-api.service";
import {HeadToHeadResponse} from "../../_models/head-to-head";
import {FootballFixturesCustomResponse} from "../../_models/football-fixtures";

@Component({
  selector: 'app-football-h2h',
  templateUrl: './football-h2h.component.html',
  styleUrl: './football-h2h.component.css'
})
export class FootballH2hComponent {

  homeTeamId!: number;
  awayTeamId!: number;
  headToHead!: HeadToHeadResponse;
  homeTeamFixtures!: FootballFixturesCustomResponse[];
  awayTeamFixtures!: FootballFixturesCustomResponse[];
  fixtureResponse!: FootballFixturesCustomResponse;

  constructor(private footballApiService: FootballApiService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.footballApiService.getFixturesById(params['matchId'])
        .then(response => {
          this.homeTeamId = response.teams.home.id;
          this.awayTeamId = response.teams.away.id;
          this.fixtureResponse = response;
          this.footballApiService.getHeadToHead(this.homeTeamId + '-' + this.awayTeamId)
            .then(response => {
              this.headToHead = response;
            })
          this.footballApiService.get5FixturesByTeamId(this.homeTeamId)
            .then(response => {
              this.homeTeamFixtures = response;
            })
          this.footballApiService.get5FixturesByTeamId(this.awayTeamId)
            .then(response => {
              this.awayTeamFixtures = response;
            })
        })

    })
  }

  matchDetails() {

  }
}
