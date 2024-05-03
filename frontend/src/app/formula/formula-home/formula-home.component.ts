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

  constructor(private formula1Api: Formula1ApiService, private footballApi: FootballApiService, private  router: Router) { }

  ngOnInit(): void {
    this.formula1Api.getRaces()
      .then(response => {
        this.races$ = response.response;
      })
  }

  toggleDetails(index: number): void {
    this.openDetails = this.openDetails === index ? null : index;
    this.formula1Api.getRankings(this.races$[index].id)
      .then(response => {
        this.ranking$ = response.response;
      })
  }

  matchDetails() {

  }

  showDriver(id: number) {
    this.router.navigate(["/drivers/" + id]);
  }
}
