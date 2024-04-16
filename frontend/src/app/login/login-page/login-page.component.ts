import {Component, Type} from '@angular/core';
import {FootballH2hComponent} from "../../football/football-h2h/football-h2h.component";
import {SignInComponent} from "../sign-in/sign-in.component";
import {SignUpComponent} from "../sign-up/sign-up.component";

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.css'
})
export class LoginPageComponent {

  currentComponent: Type<any> | null = SignInComponent;
  activeButton = 'signIn';

  signIn() {
    this.currentComponent = SignInComponent;
    this.activeButton = 'signIn';
  }

  signUp() {
    this.currentComponent = SignUpComponent;
    this.activeButton = 'signUp';
  }
}
