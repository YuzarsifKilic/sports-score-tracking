import { Component } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {NbaApiService} from "../../_services/nba-api.service";
import {NbaGamesResponse} from "../../_models/nba-games";

@Component({
  selector: 'app-basketball-h2h',
  templateUrl: './basketball-h2h.component.html',
  styleUrl: './basketball-h2h.component.css'
})
export class BasketballH2hComponent {

  homeTeamId!: number;
  visitorsTeamId!: number;
  h2hResponses!: NbaGamesResponse[];

  constructor(private route: ActivatedRoute, private nbaApiService: NbaApiService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.nbaApiService.getMatchesById(params['matchId'])
        .then(response => {
          this.homeTeamId = response.response[0].teams.home.id;
          this.visitorsTeamId = response.response[0].teams.visitors.id;
          this.nbaApiService.geth2h(this.homeTeamId + '-' + this.visitorsTeamId)
            .then(response => {
              this.h2hResponses = response.response.slice(-5);
            })
        })
    })
  }

  matchDetails() {

  }

  protected readonly parseInt = parseInt;
}
