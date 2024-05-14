import { Component } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {FootballApiService} from "../../../_services/football-api.service";
import {PlayersResponse, Statistic} from "../../../_models/football-player";

@Component({
  selector: 'app-football-team-players',
  templateUrl: './football-team-players.component.html',
  styleUrl: './football-team-players.component.css'
})
export class FootballTeamPlayersComponent {

  playerResponse!: PlayersResponse;
  currentPage: number = 1;
  isLastPage: boolean = false;
  teamId!: number;
  years: number[] = [2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023];
  selectedYear: number = 2023;


  constructor(private router: Router, private footballApiService: FootballApiService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.teamId = params['teamId'];
      this.footballApiService.getPlayers(params['teamId'], this.selectedYear, null)
        .then(response => {
          this.playerResponse = response;
          if (response.paging.total === response.paging.current) this.isLastPage = false;
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

  backPage() {
    this.currentPage--;
    this.footballApiService.getPlayers(this.teamId, this.selectedYear, this.currentPage)
      .then(response => {
        this.playerResponse = response;
      })
  }

  nextPage() {
    this.currentPage++;
    if (this.playerResponse.paging.total === this.currentPage) this.isLastPage = true;
    this.footballApiService.getPlayers(this.teamId, this.selectedYear, this.currentPage)
      .then(response => {
        this.playerResponse = response;
      })
  }

  onSelectedYear(selectedYear: number) {
    this.selectedYear = selectedYear;
    console.log(selectedYear);
    this.footballApiService.getPlayers(this.teamId, this.selectedYear, this.currentPage)
      .then(response => {
        this.playerResponse = response;
      })
  }

  playerPage(id: number) {
    this.router.navigate(['/football-player/', id]);
  }
}
