import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import Swal from 'sweetalert2';
import {AuthService} from "../../_services/auth.service";

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrl: './sign-in.component.css'
})
export class SignInComponent {

  constructor(private formBuilder: FormBuilder, private authService: AuthService) { }

  signInForm: FormGroup = this.formBuilder.group({
    email: ['', Validators.required],
    password: ['', Validators.required]
  })

  signIn(): void {
    if (this.signInForm.valid) {
      this.authService.login(this.signInForm.value.email, this.signInForm.value.password);
    }
  }
}
