import { Injectable } from '@angular/core';
import {AxiosService} from "./axios.service";
import {Country} from "../_models/country";

@Injectable({
  providedIn: 'root'
})
export class FavoriteTeamService {

  constructor(private axiosService: AxiosService) { }

  async saveFavoriteTeam(userId: string | null, teamId: number, sportType: string) {
    const resp = await this.axiosService.request(
      "POST",
      "/api/v1/favorite-teams",
      {
        userId: userId,
        teamId: teamId,
        sportType: sportType
      });
    return resp.data;
  }
}
