import { Component } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-football-home',
  templateUrl: './football-home.component.html',
  styleUrl: './football-home.component.css'
})
export class FootballHomeComponent {

  constructor(private router: Router) { }

  matchDetails() {
    this.router.navigate(['/football-match-detail']);
  }
}
