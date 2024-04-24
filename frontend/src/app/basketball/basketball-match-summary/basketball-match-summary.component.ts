import { Component } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {NbaApiService} from "../../_services/nba-api.service";
import {NbaGamesResponse} from "../../_models/nba-games";

@Component({
  selector: 'app-basketball-match-summary',
  templateUrl: './basketball-match-summary.component.html',
  styleUrl: './basketball-match-summary.component.css'
})
export class BasketballMatchSummaryComponent {

  matchId!: number;
  game!: NbaGamesResponse;

  constructor(private route: ActivatedRoute, private nbaApiService: NbaApiService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.matchId = params['matchId'];
    });
    this.nbaApiService.getMatchesById(this.matchId)
      .then(response => {
        this.game = response.response[0];
      })
  }
}
