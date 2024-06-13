import {Component, Input} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {FootballApiService} from "../../_services/football-api.service";
import {Response} from "../../_models/football-statistics";

@Component({
  selector: 'app-football-match-statistics',
  templateUrl: './football-match-statistics.component.html',
  styleUrl: './football-match-statistics.component.css'
})
export class FootballMatchStatisticsComponent {

  statistics!: Response[];

  constructor(private router: Router, private footballApiService: FootballApiService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.footballApiService.getStatistics(params['matchId'])
        .then(response => {
          this.statistics = response.response;
          console.log(response);
          console.log(this.statistics.length);
        })
    })
  }

  getHeight(statistic1: number, statistic2: number) {
    if (statistic1 == statistic2) {
      console.log(statistic1)
      console.log(statistic2)
      return 200
    }
    let sum = statistic1 + statistic2
    console.log((statistic1 / sum) * 300)
    return ((statistic1 / sum) * 300)
  }

}
