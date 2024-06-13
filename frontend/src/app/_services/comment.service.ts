import { Injectable } from '@angular/core';
import {AxiosService} from "./axios.service";
import {Comment} from "../_models/comment";

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private axiosService: AxiosService) { }

  async getCommentsByMatchId(matchId: number, sportType: string): Promise<Comment[]> {
    const resp = await this.axiosService.request(
      "GET",
      "/api/v1/comments?matchId=" + matchId + "&sportType=" + sportType,
      {});
    return resp.data;
  }

  async createComment(userId: number, matchId: number, comment: string,sportType: string)  {
    const resp = await this.axiosService.requestWithToken(
      "POST",
      "/api/v1/comments",
      {
        footballFanId: userId,
        matchId: matchId,
        comment: comment,
        sportType: sportType
      });
    return resp;
  }
}
