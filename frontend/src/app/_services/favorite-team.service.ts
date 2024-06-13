import { Injectable } from '@angular/core';
import {AxiosService} from "./axios.service";
import {Country} from "../_models/country";
import {FavoriteTeam} from "../_models/favorite-team";

@Injectable({
  providedIn: 'root'
})
export class FavoriteTeamService {

  constructor(private axiosService: AxiosService) { }

  async saveFavoriteTeam(userId: number, teamId: number, sportType: string) {
    const resp = await this.axiosService.requestWithToken(
      "POST",
      "/api/v1/favorite-teams",
      {
        userId: userId,
        teamId: teamId,
        sportType: sportType
      });
    return resp.data;
  }

  async getFavoriteTeams(userId: string): Promise<FavoriteTeam[]> {
    const resp = await this.axiosService.request(
      "GET",
      "/api/v1/favorite-teams/user/" + userId,
      {});
    return resp.data;
  }

  async deleteFavoriteTeam(userId: number, teamId: number, sportType: string) {
    const resp = await this.axiosService.request(
      "DELETE",
      "/api/v1/favorite-teams",
      {
        userId: userId,
        teamId: teamId,
        sportType: sportType
      });
    return resp.data;
  }
}
