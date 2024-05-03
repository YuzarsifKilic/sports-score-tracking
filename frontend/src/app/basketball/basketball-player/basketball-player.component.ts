import {Component, ElementRef, ViewChild} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {NbaApiService} from "../../_services/nba-api.service";
import {NbaPlayer} from "../../_models/nba-player";
import {NbaPlayerStatistics} from "../../_models/nba-player-statistics";

@Component({
  selector: 'app-basketball-player',
  templateUrl: './basketball-player.component.html',
  styleUrl: './basketball-player.component.css'
})
export class BasketballPlayerComponent {

  player!: NbaPlayer;
  statistics!: NbaPlayerStatistics;
  years: number[] = [];

  constructor(private route: ActivatedRoute, private nbaApiService: NbaApiService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.nbaApiService.getPlayerById(params['playerId'])
        .then(response => {
          console.log(response);
          this.player = response;
          this.nbaApiService.getPlayerStatisticsById(this.player.id, 2023)
            .then(response => {
              this.statistics = response;
              for (let i = this.player.nba.start; i < 2023; i++) {
                this.years.push(i);
              }
            })
        })
    })
  }

  onSelectYear($event: any) {
    this.nbaApiService.getPlayerStatisticsById(this.player.id, $event.target.value)
      .then(response => {
        this.statistics = response;
      })
  }
}
