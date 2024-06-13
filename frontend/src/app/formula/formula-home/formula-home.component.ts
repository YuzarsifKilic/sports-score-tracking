import { Component } from '@angular/core';
import {Formula1ApiService} from "../../_services/formula1-api.service";
import {RaceResponse} from "../../_models/race";
import {FootballApiService} from "../../_services/football-api.service";
import {RankingsResponse} from "../../_models/rankings";
import {Router} from "@angular/router";
import {Country} from "../../_models/country";

@Component({
  selector: 'app-formula-home',
  templateUrl: './formula-home.component.html',
  styleUrl: './formula-home.component.css'
})
export class FormulaHomeComponent {

  openDetails: number | null = null;
  races$!: RaceResponse[];
  countries!: Country[];
  ranking$!: RankingsResponse[];
  raceId!: number;

  constructor(private formula1Api: Formula1ApiService, private footballApi: FootballApiService, private  router: Router) { }

  ngOnInit(): void {
    this.formula1Api.getRaces("race")
      .then(response => {
        this.races$ = response.response;
      })
  }

  toggleDetails(index: number): void {
    this.openDetails = this.openDetails === index ? null : index;
    this.raceId = this.races$[index].id;
    this.formula1Api.getRankings(this.races$[index].id)
      .then(response => {
        this.ranking$ = response.response;
      })
  }

  showDriver(id: number) {
    this.router.navigate(["/drivers/" + id]);
  }

  changeRace(race: string) {
    if (race === "race") {
      this.formula1Api.getRankings(this.raceId)
        .then(response => {
          this.ranking$ = response.response;
        })
    } else if (race === "1st Qualifying") {
      this.formula1Api.getRankings(this.raceId + 1)
        .then(response => {
          this.ranking$ = response.response;
        })
    } else if (race === "2nd Qualifying") {
      this.formula1Api.getRankings(this.raceId + 2)
        .then(response => {
          this.ranking$ = response.response;
        })
    } else if (race === "3rd Qualifying") {
      this.formula1Api.getRankings(this.raceId + 3)
        .then(response => {
          this.ranking$ = response.response;
        })
    }
  }
}
