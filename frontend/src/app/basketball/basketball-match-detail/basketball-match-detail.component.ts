import {Component, Type} from '@angular/core';
import {FootballH2hComponent} from "../../football/football-h2h/football-h2h.component";
import {BasketballH2hComponent} from "../basketball-h2h/basketball-h2h.component";
import {ActivatedRoute, Router} from "@angular/router";
import {BasketballMatchStatisticsComponent} from "../basketball-match-statistics/basketball-match-statistics.component";
import {BasketballMatchCommentsComponent} from "../basketball-match-comments/basketball-match-comments.component";
import {BasketballMatchSummaryComponent} from "../basketball-match-summary/basketball-match-summary.component";
import {NbaApiService} from "../../_services/nba-api.service";
import {NbaGamesResponse} from "../../_models/nba-games";

@Component({
  selector: 'app-basketball-match-detail',
  templateUrl: './basketball-match-detail.component.html',
  styleUrl: './basketball-match-detail.component.css'
})
export class BasketballMatchDetailComponent {

  currentComponent: Type<any> | null = BasketballMatchSummaryComponent  ;
  matchId!: number;
  game!: NbaGamesResponse;

  constructor(private router: Router, private route: ActivatedRoute, private nbaApiService: NbaApiService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.matchId = params['matchId'];
    });
    this.nbaApiService.getMatchesById(this.matchId)
      .then(response => {
        this.game = response.response[0];
      })
  }

  h2h() {
    this.currentComponent = BasketballH2hComponent;
  }

  matchSummary() {
    this.currentComponent = BasketballMatchSummaryComponent;
  }

  matchStatistics() {
    this.currentComponent = BasketballMatchStatisticsComponent;
  }

  matchComments() {
    this.currentComponent = BasketballMatchCommentsComponent;
  }
}
