import { Component } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

  searchImage: String = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fuxwing.com%2Fsearch-icon%2F&psig=AOvVaw0OI79SVqhKZeGuyaMqC3lQ&ust=1712935413319000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCNi74vC7uoUDFQAAAAAdAAAAABAE";

  constructor(private router: Router) { }

  footballHome() {
    this.router.navigate(['football-home']);
  }

  basketballHome() {
    this.router.navigate(["basketball-home"]);
  }

  formulaHome() {
    this.router.navigate(["formula-home"]);
  }
}
