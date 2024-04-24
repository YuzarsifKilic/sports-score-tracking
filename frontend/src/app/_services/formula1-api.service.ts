import { Injectable } from '@angular/core';
import {AxiosService} from "./axios.service";
import {Race, RaceResponse} from "../_models/race";
import {Rankings} from "../_models/rankings";

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

  async getRankings(raceId: number): Promise<Rankings> {
    const resp = await this.axios.request(
      "GET",
      "/api/formula-api/rankings/" + raceId,
      {});
    return resp.data;
  }
}
