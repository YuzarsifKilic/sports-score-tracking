import { Component } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../_services/auth.service";
import {FootballFanService} from "../../_services/football-fan.service";
import Swal from "sweetalert2";
import {Router} from "@angular/router";

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.css'
})
export class SignUpComponent {

  constructor(private formBuilder: FormBuilder, private footballFanService: FootballFanService, private router: Router) { }

  isSubmitted: boolean = false;

  signInForm: FormGroup = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required, Validators.minLength(8), Validators.maxLength(20)]),
    firstName: new FormControl('', [Validators.required, Validators.minLength(2)]),
    lastName: new FormControl('', [Validators.required, Validators.minLength(2)]),
    phoneNumber: new FormControl('', [Validators.required, Validators.minLength(10) , Validators.maxLength(10)])
  })

  get formControls() {
    return this.signInForm.controls;
  }

  signUp() {
    this.isSubmitted = true;
    if (this.signInForm.valid) {
      this.footballFanService.addFootballFan(this.signInForm.value.email, this.signInForm.value.password,
        this.signInForm.value.firstName, this.signInForm.value.lastName, this.signInForm.value.phoneNumber)
        .then(response => {
          if (response.status === 200) {
            this.successAlert();
          }
        })
        .catch(error => {
          this.errorAlert();
        })
    }
  }

  successAlert() {
    Swal.fire({
      title: "Başarılı",
      html: "Bilgileriniz başarıyla kaydedildi. Anasayfaya yönlendiriliyorsunuz...",
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
      title: "Hata",
      html: "Bilgilerinizi kontrol ediniz...",
      icon: 'error',
    });
  }
}
