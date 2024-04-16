import {Component, Type} from '@angular/core';
import {FootballH2hComponent} from "../../football/football-h2h/football-h2h.component";
import {BasketballH2hComponent} from "../basketball-h2h/basketball-h2h.component";
import {Router} from "@angular/router";
import {BasketballMatchStatisticsComponent} from "../basketball-match-statistics/basketball-match-statistics.component";
import {BasketballMatchCommentsComponent} from "../basketball-match-comments/basketball-match-comments.component";
import {BasketballMatchSummaryComponent} from "../basketball-match-summary/basketball-match-summary.component";

@Component({
  selector: 'app-basketball-match-detail',
  templateUrl: './basketball-match-detail.component.html',
  styleUrl: './basketball-match-detail.component.css'
})
export class BasketballMatchDetailComponent {

  currentComponent: Type<any> | null = BasketballMatchSummaryComponent  ;

  constructor(private router: Router) { }

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
