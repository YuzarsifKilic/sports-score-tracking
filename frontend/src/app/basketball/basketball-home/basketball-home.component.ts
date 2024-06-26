import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {NbaApiService} from "../../_services/nba-api.service";
import {NbaGames, NbaGamesResponse} from "../../_models/nba-games";
import Swal from "sweetalert2";

@Component({
  selector: 'app-basketball-home',
  templateUrl: './basketball-home.component.html',
  styleUrl: './basketball-home.component.css'
})
export class BasketballHomeComponent {

  nbaGames!: NbaGamesResponse[];
  favoriteGames!: NbaGames;
  favoriteIcon: string = "assets/images/favorite-empty-star-icon.png";
  selectedFavoriteIcon: string = "assets/images/favorite-star-icon.png";
  selected: boolean = false;


  constructor(private router: Router, private nbaApiService: NbaApiService) { }

  matchDetails(id: number) {
    this.router.navigate(['/basketball-match-detail/' + id]);
  }

  ngOnInit(): void {
    this.nbaApiService.getMatchesByDate(this.dateConvert())
      .then(response => {
        this.nbaGames = response.response;
      })
    if (window.localStorage.getItem("user_id") != null) {
      this.nbaApiService.getFavoriteMatchesByUser(parseInt(window.localStorage.getItem("user_id")!), this.dateConvert())
        .then(response => {
          this.favoriteGames = response;
        })
    }

  }

  dateConvert() {
    let current = new Date();
    let day = ("0" + current.getDate()).slice(-2);
    let month = ("0" + (current.getMonth() + 1)).slice(-2);
    let year = current.getFullYear();
    return `${year}-${month}-${day}`;
  }

    protected readonly parseInt = parseInt;

  toggleImage() {
    this.selected = !this.selected;
  }

  changeImage() {
    return this.selected ? this.selectedFavoriteIcon : this.favoriteIcon;
  }

  async setDate() {
    const { value: date } = await Swal.fire({
      title: "Fikstür Tarihini Girin",
      input: "date",
    });
    this.nbaApiService.getMatchesByDate(date)
      .then(response => {
        this.nbaGames = response.response;
      })
  }
}
