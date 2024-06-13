import { Component } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {FootballApiService} from "../../../_services/football-api.service";
import {NbaApiService} from "../../../_services/nba-api.service";
import {NbaStandings} from "../../../_models/nba-standings";

@Component({
  selector: 'app-basketball-team-standings',
  templateUrl: './basketball-team-standings.component.html',
  styleUrl: './basketball-team-standings.component.css'
})
export class BasketballTeamStandingsComponent {

  standings!: NbaStandings;
  teamId!: number;

  constructor(private router: Router, private nbaApiService: NbaApiService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.teamId = params['teamId'];
      this.nbaApiService.getStandings(2023, "standard")
        .then(response => {
          this.standings = response;
        })
    })
  }

}
