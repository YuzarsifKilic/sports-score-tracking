import { Injectable } from '@angular/core';
import axios from "axios";

@Injectable({
  providedIn: 'root'
})
export class AxiosService {

  constructor() {
    axios.defaults.baseURL = 'http://localhost:8080';
    axios.defaults.headers.post['Content-Type'] = 'application/json';
  }

  getAuthToken(): string | null {
    return window.localStorage.getItem("auth_token");
  }

  setAuthTokenAndRole(token: string | null, role: string | null, id: string | null): void {
    if (token !== null && role !== null && id !== null) {
      window.localStorage.setItem("auth_token", token);
      window.localStorage.setItem("auth_role", role);
      window.localStorage.setItem("user_id", id);
    } else {
      window.localStorage.removeItem("auth_token");
      window.localStorage.removeItem("auth_role");
      window.localStorage.removeItem("user_id");
    }
  }

  request(method: string, url: string, data: any): Promise<any> {
    return axios({
      method: method,
      url: url,
      data: data
    });
  }

  requestWithToken(method: string, url: string, data: any): Promise<any> {
    let headers: any = {};

    if (this.getAuthToken() !== null) {
      headers = {"Authorization": "Bearer " + this.getAuthToken()};
    }

    return axios({
      method: method,
      url: url,
      data: data,
      headers: headers
    });
  }
}
