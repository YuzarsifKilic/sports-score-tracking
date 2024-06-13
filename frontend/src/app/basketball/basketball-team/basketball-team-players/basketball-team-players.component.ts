import { Component } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {FootballApiService} from "../../../_services/football-api.service";
import {NbaApiService} from "../../../_services/nba-api.service";
import {NbaPlayerResponse} from "../../../_models/nba-player";

@Component({
  selector: 'app-basketball-team-players',
  templateUrl: './basketball-team-players.component.html',
  styleUrl: './basketball-team-players.component.css'
})
export class BasketballTeamPlayersComponent {

  players!: NbaPlayerResponse;
  years: number[] = [2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023];
  selectedYear: number = 2023;
  teamId!: number;

  constructor(private router: Router, private nbaApiService: NbaApiService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.teamId = params['teamId'];
      this.nbaApiService.getPlayersByTeam(2023, params['teamId'])
        .then(response =>{
          this.players = response;
        })
    })
  }

  onSelectedYear(selectedYear: number) {
    this.selectedYear = selectedYear;
    console.log(selectedYear);
    this.nbaApiService.getPlayersByTeam(selectedYear, this.teamId)
      .then(response => {
        this.players = response;
      })
  }

  playerPage(id: number) {
    this.router.navigate(['basketball-player/', id]);
  }
}
