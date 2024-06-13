import {Component, Renderer2} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {FootballApiService} from "../../_services/football-api.service";
import {LineUpsResponse} from "../../_models/football-lineup";
declare var widgets: any;
@Component({
  selector: 'app-football-match-summary',
  templateUrl: './football-match-summary.component.html',
  styleUrl: './football-match-summary.component.css'
})
export class FootballMatchSummaryComponent {

  lineupResponse!: LineUpsResponse;

  constructor(private router: Router, private footballApiService: FootballApiService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.footballApiService.getLineup(params['matchId'])
        .then(response => {
          this.lineupResponse = response;
          console.log(this.lineupResponse.response.length);
        })
    })
  }

}
