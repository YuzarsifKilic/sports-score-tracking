import { Injectable } from '@angular/core';
import {AxiosService} from "./axios.service";
import {ToastrService} from "ngx-toastr";
import {BehaviorSubject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private username = new BehaviorSubject<string>("");
  username$ = this.username.asObservable();
  private loggedIn = new BehaviorSubject<boolean>(false);
  loggedIn$ = this.loggedIn.asObservable();

  constructor(private axios: AxiosService, private toastr: ToastrService) { }

  login(email: string, password: string) {
    this.axios.request(
      "POST",
      "/api/auth/login",
      {
        email: email,
        password: password
      }).then(resp => {
      this.axios.setAuthTokenAndRole(resp.data.token, resp.data.roles[0], resp.data.id)
      this.loggedIn.next(true);
    }).catch(error => {
      this.toastr.error("Bad Credentials", "Error")
    })
  }
}
