import { Injectable } from '@angular/core';
import {AxiosService} from "./axios.service";

@Injectable({
  providedIn: 'root'
})
export class FavoriteMatchService {

  constructor(private axiosService: AxiosService) { }

  async checkFavoriteMatch(userId: number, matchId: number, sportType: string): Promise<boolean> {
    const resp = await this.axiosService.request(
      "POST",
      "/api/v1/favorite-matches/check",
      {
        userId: userId,
        matchId: matchId,
        sportType: sportType
      });
    return resp.data;
  }

  async addFavoriteMatch(userId: number, matchId: number, sportType: string) {
    const resp = await this.axiosService.requestWithToken(
      "POST",
      "/api/v1/favorite-matches",
      {
        userId: userId,
        matchId: matchId,
        sportType: sportType
      });
    return resp.data;
  }

  async deleteFavoriteMatch(userId: number, matchId: number, sportType: string) {
    const resp = await this.axiosService.request(
      "DELETE",
      "/api/v1/favorite-matches",
      {
        userId: userId,
        matchId: matchId,
        sportType: sportType
      });
    return resp.data;
  }
}
