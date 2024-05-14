import { Component } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {FootballApiService} from "../../_services/football-api.service";
import {SinglePlayersResponse, Statistic} from "../../_models/football-single-player";

@Component({
  selector: 'app-football-player',
  templateUrl: './football-player.component.html',
  styleUrl: './football-player.component.css'
})
export class FootballPlayerComponent {

  playerResponse!: SinglePlayersResponse;
  statistics!: Statistic[];
  playerId!: number;
  years: number[] = [2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023];
  selectedYear: number = 2023;


  constructor(private router: Router, private footballApiService: FootballApiService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.playerId = params['playerId'];
      this.footballApiService.getPlayersById(params['playerId'], this.selectedYear)
        .then(response => {
          this.playerResponse = response;
          this.playerResponse.response.map(item => this.statistics = item.statistics)
        })
    })
  }

  getLineups(statistics: Statistic[]) {
    let sum = 0;
    for (let i = 0; i < statistics.length; i++) {
      sum += statistics[i].games.lineups
    }
    return sum
  }

  getMinutes(statistics: Statistic[]) {
    let sum = 0;
    for (let i = 0; i < statistics.length; i++) {
      sum += statistics[i].games.minutes
    }
    return sum
  }

  getGoals(statistics: Statistic[]) {
    let sum = 0;
    for (let i = 0; i < statistics.length; i++) {
      sum += statistics[i].goals.total
    }
    return sum
  }

  getAssists(statistics: Statistic[]) {
    let sum = 0;
    for (let i = 0; i < statistics.length; i++) {
      sum += statistics[i].goals.assists
    }
    return sum
  }

  getRedCards(statistics: Statistic[]) {
    let sum = 0;
    for (let i = 0; i < statistics.length; i++) {
      sum += statistics[i].cards.red
    }
    return sum
  }

  getYellowCards(statistics: Statistic[]) {
    let sum = 0;
    for (let i = 0; i < statistics.length; i++) {
      sum += statistics[i].cards.yellow
    }
    return sum
  }

  onSelectedYear(selectedYear: number) {
    this.selectedYear = selectedYear;
    console.log(selectedYear);
    this.footballApiService.getPlayersById(this.playerId, this.selectedYear)
      .then(response => {
        this.playerResponse = response;
        this.playerResponse.response.map(item => this.statistics = item.statistics)
      })
  }
}
