import { Component } from '@angular/core';
import {Formula1ApiService} from "../../_services/formula1-api.service";
import {RaceResponse} from "../../_models/race";
import {FootballApiService} from "../../_services/football-api.service";
import {CountryResponse} from "../../_models/country";
import {RankingsResponse} from "../../_models/rankings";

@Component({
  selector: 'app-formula-home',
  templateUrl: './formula-home.component.html',
  styleUrl: './formula-home.component.css'
})
export class FormulaHomeComponent {

  openDetails: number | null = null;
  races$!: RaceResponse[];
  countries!: CountryResponse[];
  ranking$!: RankingsResponse[];

  constructor(private formula1Api: Formula1ApiService, private footballApi: FootballApiService) { }

  ngOnInit(): void {
    this.formula1Api.getRaces()
      .then(response => {
        this.races$ = response.response;
        this.races$.forEach(race => {
          this.footballApi.getCountriesByName(race.competition.location.country.toLowerCase())
            .then(response => {
              this.countries.push(response.response[0]);
            })
        })
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
}
