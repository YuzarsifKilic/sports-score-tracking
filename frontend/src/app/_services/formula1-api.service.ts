import { Injectable } from '@angular/core';
import {AxiosService} from "./axios.service";
import {Race, RaceResponse} from "../_models/race";
import {Rankings} from "../_models/rankings";
import {Drivers, DriversResponse} from "../_models/drivers";
import {Country} from "../_models/country";
import {FormulaTeam} from "../_models/formula-team";

@Injectable({
  providedIn: 'root'
})
export class Formula1ApiService {

  constructor(private axios: AxiosService) { }

  async getRaces(): Promise<Race> {
    const resp = await this.axios.request(
      "GET",
      "/api/formula-api/races",
      {});
    return resp.data;
  }

  async getCountriesByName(name: string): Promise<Country> {
    const resp = await this.axios.request(
      "GET",
      "/api/v1/countries?name=" + name,
      {});
    return resp.data;
  }

  async getRankings(raceId: number): Promise<Rankings> {
    const resp = await this.axios.request(
      "GET",
      "/api/formula-api/rankings/" + raceId,
      {});
    return resp.data;
  }

  async getTeamById(teamId: number): Promise<FormulaTeam> {
    const resp = await this.axios.request(
      "GET",
      "/api/formula-api/teams?teamId=" + teamId,
      {});
    return resp.data;
  }

  async getDriversById(driverId: number): Promise<Drivers> {
    const resp = await this.axios.request(
      "GET",
      "/api/formula-api/drivers?driverId=" + driverId,
      {});
    return resp.data;
  }
}
