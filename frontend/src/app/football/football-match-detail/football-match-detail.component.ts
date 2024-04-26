import {Component, Type} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {FootballH2hComponent} from "../football-h2h/football-h2h.component";
import {FootballMatchSummaryComponent} from "../football-match-summary/football-match-summary.component";
import {FootballMatchStatisticsComponent} from "../football-match-statistics/football-match-statistics.component";
import {FootballMatchCommentsComponent} from "../football-match-comments/football-match-comments.component";
import {FootballApiService} from "../../_services/football-api.service";
import {FootballFixturesCustomResponse} from "../../_models/football-fixtures";

@Component({
  selector: 'app-football-match-detail',
  templateUrl: './football-match-detail.component.html',
  styleUrl: './football-match-detail.component.css'
})
export class FootballMatchDetailComponent {

  currentComponent: Type<any> | null = FootballH2hComponent;
  fixtureDetail!: FootballFixturesCustomResponse;

  constructor(private router: Router, private footballApiService: FootballApiService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.footballApiService.getFixturesById(params['matchId'])
        .then(response => {
          this.fixtureDetail = response;
        })
    })
  }

  h2h() {
    this.currentComponent = FootballH2hComponent;
  }

  matchSummary() {
    this.currentComponent = FootballMatchSummaryComponent;
  }

  matchStatistics() {
    this.currentComponent = FootballMatchStatisticsComponent;
  }

  matchComments() {
    this.currentComponent = FootballMatchCommentsComponent;
  }
}
