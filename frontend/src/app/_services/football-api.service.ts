import { Injectable } from '@angular/core';
import {AxiosService} from "./axios.service";
import {Country} from "../_models/country";
import {LeagueResponse} from "../_models/league";
import {FootballFixtures, FootballFixturesCustomResponse, FootballFixturesResponse} from "../_models/football-fixtures";
import {HeadToHeadResponse} from "../_models/head-to-head";

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

  async getFixturesByDate(date: string): Promise<FootballFixturesResponse[]> {
    const resp = await this.axios.request(
      "GET",
      "/api/football-api/fixtures?date=" + date,
      {});
    return resp.data;
  }

  async getFixturesById(id: string): Promise<FootballFixturesCustomResponse> {
    const resp = await this.axios.request(
      "GET",
      "/api/football-api/fixtures/" + id,
      {});
    return resp.data;
  }

  async get5FixturesByTeamId(id: number): Promise<FootballFixturesCustomResponse[]> {
    const resp = await this.axios.request(
      "GET",
      "/api/football-api/last-fixtures/" + id,
      {});
    return resp.data;
  }

  async getHeadToHead(h2h: string): Promise<HeadToHeadResponse> {
    const resp = await this.axios.request(
      "GET",
      "/api/football-api/head-to-head?h2h=" + h2h,
      {});
    return resp.data;
  }
}
