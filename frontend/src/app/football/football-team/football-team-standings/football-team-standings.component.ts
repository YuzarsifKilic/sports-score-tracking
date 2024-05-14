import { Component } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {FootballApiService} from "../../../_services/football-api.service";
import {FootballStandings, FootballStandingsResponse} from "../../../_models/football-standings";

@Component({
  selector: 'app-football-team-standings',
  templateUrl: './football-team-standings.component.html',
  styleUrl: './football-team-standings.component.css'
})
export class FootballTeamStandingsComponent {

  teamStandings!: FootballStandingsResponse;
  leagueStandings!: FootballStandings[];


  constructor(private router: Router, private footballApiService: FootballApiService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.footballApiService.getStandingsByTeam(params['teamId'], 2023)
        .then(response => {
          this.teamStandings = response;
          this.footballApiService.getStandings(this.teamStandings.response[0].league.id, 2023)
            .then(response => {
              this.leagueStandings = response;
            })
        })
    })
  }

  splitForm(form: string) {
    console.log(form.split(""));
    return form.split("");
  }
}
