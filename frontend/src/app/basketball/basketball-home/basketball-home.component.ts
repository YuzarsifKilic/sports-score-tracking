import { Component } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-basketball-home',
  templateUrl: './basketball-home.component.html',
  styleUrl: './basketball-home.component.css'
})
export class BasketballHomeComponent {

  constructor(private router: Router) { }

  matchDetails() {
    this.router.navigate(['/basketball-match-detail']);
  }
}
