import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {FootballApiService} from "../../_services/football-api.service";
import {FootballFixturesResponse} from "../../_models/football-fixtures";

@Component({
  selector: 'app-football-home',
  templateUrl: './football-home.component.html',
  styleUrl: './football-home.component.css'
})
export class FootballHomeComponent {

  fixtures!: FootballFixturesResponse[];

  constructor(private router: Router, private footballApiService: FootballApiService) { }

  ngOnInit(): void {
    this.footballApiService.getFixturesByDate(this.dateConvert())
      .then(response => {
        this.fixtures = response;
        console.log(this.fixtures);
      })
  }

  matchDetails(id: number) {
    this.router.navigate(['/football-match-detail/' + id]);
  }

  dateConvert() {
    let current = new Date();
    let day = ("0" + current.getDate()).slice(-2);
    let month = ("0" + (current.getMonth() + 1)).slice(-2);
    let year = current.getFullYear();
    return `${year}-${month}-${day}`;
  }
}
