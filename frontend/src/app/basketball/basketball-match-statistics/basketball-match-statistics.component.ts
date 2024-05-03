import { Component } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {NbaApiService} from "../../_services/nba-api.service";
import {NbaMatchesStatisticsResponse} from "../../_models/nba-matches-statistics";

@Component({
  selector: 'app-basketball-match-statistics',
  templateUrl: './basketball-match-statistics.component.html',
  styleUrl: './basketball-match-statistics.component.css'
})
export class BasketballMatchStatisticsComponent {

  height1 =  200;
  height2 =  120;
  matchId!: number;
  matchStatistics!: NbaMatchesStatisticsResponse[];

  constructor(private route: ActivatedRoute, private nbaApiService: NbaApiService, private router: Router) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.matchId = params['matchId'];
    });
    this.nbaApiService.getStatisticsById(this.matchId)
      .then(response => {
        this.matchStatistics = response.response;
      })
  }

  playerDetail(id: number) {
    this.router.navigate(['/basketball-player/' + id]);
  }
}
