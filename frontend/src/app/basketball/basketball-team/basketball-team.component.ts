import {Component, Type} from '@angular/core';
import {
  FootballTeamFixtureComponent
} from "../../football/football-team/football-team-fixture/football-team-fixture.component";
import {BasketballTeamStandingsComponent} from "./basketball-team-standings/basketball-team-standings.component";
import {ActivatedRoute, Router} from "@angular/router";
import {FootballApiService} from "../../_services/football-api.service";
import {NbaApiService} from "../../_services/nba-api.service";
import {TeamResponse} from "../../_models/nba-team";
import {BasketballTeamPlayersComponent} from "./basketball-team-players/basketball-team-players.component";
import {BasketballTeamGamesComponent} from "./basketball-team-games/basketball-team-games.component";

@Component({
  selector: 'app-basketball-team',
  templateUrl: './basketball-team.component.html',
  styleUrl: './basketball-team.component.css'
})
export class BasketballTeamComponent {

  currentComponent: Type<any> | null = BasketballTeamGamesComponent;
  basketballTeam!: TeamResponse

  constructor(private router: Router, private nbaApiService: NbaApiService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.nbaApiService.getTeamById(params['teamId'])
        .then(response => {
          this.basketballTeam = response;
        })
    })
  }

  fixture() {
    this.currentComponent = BasketballTeamGamesComponent;
  }

  players() {
    this.currentComponent = BasketballTeamPlayersComponent;
  }

  standings() {
    this.currentComponent = BasketballTeamStandingsComponent;
  }
}
