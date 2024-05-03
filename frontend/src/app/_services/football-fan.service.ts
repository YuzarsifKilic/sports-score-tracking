import { Injectable } from '@angular/core';
import {AxiosService} from "./axios.service";

@Injectable({
  providedIn: 'root'
})
export class FootballFanService {

  constructor(private axios: AxiosService) { }

  addFootballFan(email: string, password: string, firstName: string, lastName: string, phoneNumber: string) {
    return this.axios.request(
      "POST",
      "/api/v1/football-fans",
      {
        email: email,
        password: password,
        firstName: firstName,
        lastName: lastName,
        phoneNumber: phoneNumber});
  }

  findFootballFanById(userId: number) {
    return this.axios.request(
      "GET",
      "/api/v1/football-fans/" + userId,
      {});
  }
}
