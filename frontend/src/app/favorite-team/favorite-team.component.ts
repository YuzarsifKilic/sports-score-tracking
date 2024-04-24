import { Component } from '@angular/core';
import {FootballApiService} from "../_services/football-api.service";
import {Country, CountryResponse} from "../_models/country";
import {League, LeagueResponse, Response} from "../_models/league";

@Component({
  selector: 'app-favorite-team',
  templateUrl: './favorite-team.component.html',
  styleUrl: './favorite-team.component.css'
})
export class FavoriteTeamComponent {

  countries$!: CountryResponse[];
  countryName!: string;
  country!: CountryResponse;
  selectedCountry: boolean = false;
  leagues$!: Response[];
  league!: League;

    constructor(private footballApiService: FootballApiService) { }

  ngOnInit(): void {
    this.footballApiService.getCountries()
      .then(response => {
        this.countries$ = response.response;
      })
  }

  onSelectCountry($event: any) {
    this.countryName = $event.target.value;
    this.selectedCountry = true;
    this.footballApiService.getCountriesByName(this.countryName.toLowerCase())
      .then(response => {
        this.country = response.response[0];
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
