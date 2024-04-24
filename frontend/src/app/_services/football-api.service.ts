import { Injectable } from '@angular/core';
import {AxiosService} from "./axios.service";
import {Country} from "../_models/country";
import {LeagueResponse} from "../_models/league";

@Injectable({
  providedIn: 'root'
})
export class FootballApiService {

  constructor(private axios: AxiosService) { }

  async getCountries(): Promise<Country> {
    const resp = await this.axios.request(
      "GET",
      "/api/football-api/countries",
      {});
    return resp.data;
  }

  async getCountriesByName(name: string): Promise<Country> {
    const resp = await this.axios.request(
      "GET",
      "/api/football-api/countries?name=" + name,
      {});
    return resp.data;
  }

  async getLeaguesByCountryCode(countryCode: string): Promise<LeagueResponse> {
    const resp = await this.axios.request(
      "GET",
      "/api/football-api/leagues?code=" + countryCode,
      {});
    return resp.data;
  }


}
