import {Component, Type} from '@angular/core';
import {Router} from "@angular/router";
import {FootballH2hComponent} from "../football-h2h/football-h2h.component";
import {FootballMatchSummaryComponent} from "../football-match-summary/football-match-summary.component";
import {FootballMatchStatisticsComponent} from "../football-match-statistics/football-match-statistics.component";
import {FootballMatchCommentsComponent} from "../football-match-comments/football-match-comments.component";

@Component({
  selector: 'app-football-match-detail',
  templateUrl: './football-match-detail.component.html',
  styleUrl: './football-match-detail.component.css'
})
export class FootballMatchDetailComponent {

  currentComponent: Type<any> | null = FootballH2hComponent;

  constructor(private router: Router) { }

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
