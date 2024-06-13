import { Injectable } from '@angular/core';
import {AxiosService} from "./axios.service";
import {NbaGames} from "../_models/nba-games";
import {NbaMatchesStatistics} from "../_models/nba-matches-statistics";
import {NbaPlayer, NbaPlayerResponse} from "../_models/nba-player";
import {NbaPlayerStatistics} from "../_models/nba-player-statistics";
import {TeamResponse} from "../_models/nba-team";
import {NbaStandings} from "../_models/nba-standings";

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


  async getTeamById(teamId: number): Promise<TeamResponse> {
    const resp = await this.axios.request(
      "GET",
      "/api/nba-api/teams?teamId=" + teamId,
      {});
    return resp.data;
  }

  async getStandings(season: number, league: string): Promise<NbaStandings> {
    const resp = await this.axios.request(
      "GET",
      "/api/nba-api/standings?season=" + season + "&league=" + league,
      {});
    return resp.data;
  }

  async getPlayersByTeam(season: number, teamId: number): Promise<NbaPlayerResponse> {
    const resp = await this.axios.request(
      "GET",
      "/api/nba-api/team/players?season=" + season + "&teamId=" + teamId,
      {});
    return resp.data;
  }

  async getMatchesByTeam(teamId: number, season: number): Promise<NbaGames> {
    const resp = await this.axios.request(
      "GET",
      "/api/nba-api/team/games?teamId=" + teamId + "&season=" + season,
      {});
    return resp.data;
  }

  async getFavoriteMatchesByUser(userId: number, date: string): Promise<NbaGames> {
    const resp = await this.axios.request(
      "GET",
      "/api/nba-api/games/user?userId=" + userId + "&date=" + date,
      {});
    return resp.data;
  }
}
