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

  @Input() teamAGoals: number = 6;
  @Input() teamBGoals: number = 4;
  @Input() teamAStats: number = 50; // Takım A'nın yüzdesi
  @Input() teamBStats: number = 50; // Takım B'nın yüzdesi
  height1 =  200;
  height2 =  120;

  statistics!: Response[];

  constructor(private router: Router, private footballApiService: FootballApiService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.footballApiService.getStatistics(params['matchId'])
        .then(response => {
          this.statistics = response.response;
          console.log(response);
        })
    })
  }

  getHeight(statistic1: number, statistic2: number) {
    let sum = statistic1 + statistic2
    console.log((statistic1 / sum) * 300)
    return ((statistic1 / sum) * 300)
  }

}
