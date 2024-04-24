import { Injectable } from '@angular/core';
import {AxiosService} from "./axios.service";
import {NbaGames} from "../_models/nba-games";
import {NbaMatchesStatistics} from "../_models/nba-matches-statistics";

@Injectable({
  providedIn: 'root'
})
export class NbaApiService {

  constructor(private axios: AxiosService) { }

  async getMatchesByDate(date: string): Promise<NbaGames> {
    const resp = await this.axios.request(
      "GET",
      "/api/nba-api/games?date=" + date,
      {});
    return resp.data;
  }

  async getMatchesById(id: number): Promise<NbaGames> {
    const resp = await this.axios.request(
      "GET",
      "/api/nba-api/games?matchId=" + id,
      {});
    return resp.data;
  }

  async getStatisticsById(id: number): Promise<NbaMatchesStatistics> {
    const resp = await this.axios.request(
      "GET",
      "/api/nba-api/games/statistics?matchId=" + id,
      {});
    return resp.data;
  }
}
