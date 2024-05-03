import { Component } from '@angular/core';
import {FootballApiService} from "../_services/football-api.service";
import {Country} from "../_models/country";
import {League, LeagueResponse, Response} from "../_models/league";
import {Formula1ApiService} from "../_services/formula1-api.service";

@Component({
  selector: 'app-favorite-team',
  templateUrl: './favorite-team.component.html',
  styleUrl: './favorite-team.component.css'
})
export class FavoriteTeamComponent {

  countries$!: Country[];
  countryName!: string;
  country!: Country;
  selectedCountry: boolean = false;
  leagues$!: Response[];
  league!: League;

    constructor(private footballApiService: FootballApiService, private formula1ApiService: Formula1ApiService) { }

  ngOnInit(): void {
    this.footballApiService.getCountries()
      .then(response => {
        this.countries$ = response;
      })
  }

  onSelectCountry($event: any) {
    this.countryName = $event.target.value;
    this.selectedCountry = true;
    this.formula1ApiService.getCountriesByName(this.countryName.toLowerCase())
      .then(response => {
        this.country = response;
        this.footballApiService.getLeaguesByCountryCode(this.country.code)
          .then(response => {
            this.leagues$ = response.response;
          })
      })
  }

  onSelectLeague(item: League) {
    console.log(item);
  }


}
