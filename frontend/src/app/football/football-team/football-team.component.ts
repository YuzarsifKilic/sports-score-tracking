import {Component, Type} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {FootballApiService} from "../../_services/football-api.service";
import {FootballTeam} from "../../_models/football-team";
import {FootballH2hComponent} from "../football-h2h/football-h2h.component";
import {FootballTeamFixtureComponent} from "./football-team-fixture/football-team-fixture.component";
import {FootballMatchSummaryComponent} from "../football-match-summary/football-match-summary.component";
import {FootballMatchStatisticsComponent} from "../football-match-statistics/football-match-statistics.component";
import {FootballMatchCommentsComponent} from "../football-match-comments/football-match-comments.component";
import {FootballTeamPlayersComponent} from "./football-team-players/football-team-players.component";
import {FootballTeamStandingsComponent} from "./football-team-standings/football-team-standings.component";

@Component({
  selector: 'app-football-team',
  templateUrl: './football-team.component.html',
  styleUrl: './football-team.component.css'
})
export class FootballTeamComponent {

  currentComponent: Type<any> | null = FootballTeamFixtureComponent;
  footballTeam!: FootballTeam;

  constructor(private router: Router, private footballApiService: FootballApiService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.footballApiService.getTeamById(params['teamId'])
        .then(response => {
          this.footballTeam = response;
        })
    })
  }

  fixture() {
    this.currentComponent = FootballTeamFixtureComponent;
  }

  players() {
    this.currentComponent = FootballTeamPlayersComponent;
  }

  standings() {
    this.currentComponent = FootballTeamStandingsComponent;
  }

}
