import { Injectable } from '@angular/core';
import {AxiosService} from "./axios.service";
import {NbaGames} from "../_models/nba-games";
import {NbaMatchesStatistics} from "../_models/nba-matches-statistics";
import {NbaPlayer} from "../_models/nba-player";
import {NbaPlayerStatistics} from "../_models/nba-player-statistics";

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

  async geth2h(h2h: string): Promise<NbaGames> {
    const resp = await this.axios.request(
      "GET",
      "/api/nba-api/games?h2h=" + h2h,
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

  async getPlayerById(playerId: number): Promise<NbaPlayer> {
    const resp = await this.axios.request(
      "GET",
      "/api/nba-api/players?playerId=" + playerId,
      {});
    return resp.data;
  }

  async getPlayerStatisticsById(playerId: number, season: number): Promise<NbaPlayerStatistics> {
    const resp = await this.axios.request(
      "GET",
      `/api/nba-api/players/statistics?playerId=${playerId}&season=${season}`,
      {});
    return resp.data;
  }
}
