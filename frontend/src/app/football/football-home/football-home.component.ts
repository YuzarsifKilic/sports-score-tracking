import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {FootballApiService} from "../../_services/football-api.service";
import {FootballFixturesResponse} from "../../_models/football-fixtures";
import Swal from "sweetalert2";
import {FootballFavoriteMatches, Response} from "../../_models/football-favorite-matches";

@Component({
  selector: 'app-football-home',
  templateUrl: './football-home.component.html',
  styleUrl: './football-home.component.css'
})
export class FootballHomeComponent {

  fixtures!: FootballFixturesResponse[];
  fixtures2$!: Promise<FootballFixturesResponse[]>
  favoriteMatches$!: Promise<FootballFavoriteMatches>;

  constructor(private router: Router, private footballApiService: FootballApiService) { }

  ngOnInit(): void {
    if (window.localStorage.getItem("user_id") != null && window.localStorage.getItem("user_id") != "") {
      this.favoriteMatches$ = this.footballApiService.getFavoriteMatchesByUser(parseInt(window.localStorage.getItem("user_id")!), this.dateConvert());
      this.footballApiService.getFavoriteMatchesByUser(parseInt(window.localStorage.getItem("user_id")!), this.dateConvert())
        .then(response => {
          console.log(response);
        })
    }
    this.fixtures2$ = this.footballApiService.getFixturesByDate(this.dateConvert())
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

  async setDate() {
    const { value: date } = await Swal.fire({
      title: "Fikstür Tarihini Girin",
      input: "date",
    });
    this.fixtures2$ = this.footballApiService.getFixturesByDate(date)
    this.favoriteMatches$ = this.footballApiService.getFavoriteMatchesByUser(parseInt(window.localStorage.getItem("user_id")!), date);
  }
}
