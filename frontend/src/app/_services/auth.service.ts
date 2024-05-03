import { Injectable } from '@angular/core';
import {AxiosService} from "./axios.service";
import {BehaviorSubject} from "rxjs";
import Swal from "sweetalert2";
import {Router} from "@angular/router";
import {FootballFanService} from "./football-fan.service";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private username = new BehaviorSubject<string>("");
  username$ = this.username.asObservable();
  private loggedIn = new BehaviorSubject<boolean>(false);
  loggedIn$ = this.loggedIn.asObservable();

  constructor(private axios: AxiosService, private router: Router, private footballFanService: FootballFanService) { }

  login(email: string, password: string) {
    this.axios.request(
      "POST",
      "/api/auth/login",
      {
        email: email,
        password: password
      }).then(resp => {
      this.axios.setAuthTokenAndRole(resp.data.token, resp.data.role, resp.data.id)
      this.loggedIn.next(true);
      this.footballFanService.findFootballFanById(resp.data.id)
        .then(response => {
          this.username.next(response.data.firstName + " " + response.data.lastName);
        })
      this.successAlert();
    }).catch(error => {
      this.errorAlert();
    })
  }

  successAlert() {
    Swal.fire({
      title: "Giriş Başarılı",
      html: "Giriş başarılı anasayfaya yönlendiriliyosunuz...",
      icon: 'success',
      timer: 3000,
      timerProgressBar: true,
    }).then((result) => {
      if (result.dismiss === Swal.DismissReason.timer) {
        this.router.navigate(["football-home"]);
      }
    });
  }

  errorAlert() {
    Swal.fire({
      title: "Giriş Başarısız",
      html: "Giriş başarısız giriş bilgilerini tekrar giriniz...",
      icon: 'error',
    });
  }
}
