import { Component } from '@angular/core';
import {FootballApiService} from "../_services/football-api.service";
import {Country} from "../_models/country";
import {League, LeagueResponse, Response} from "../_models/league";
import {Formula1ApiService} from "../_services/formula1-api.service";
import {FootballTeam, Team} from "../_models/football-team";
import Swal from "sweetalert2";
import {Router} from "@angular/router";
import {FavoriteTeamService} from "../_services/favorite-team.service";

@Component({
  selector: 'app-favorite-team',
  templateUrl: './favorite-team.component.html',
  styleUrl: './favorite-team.component.css'
})
export class FavoriteTeamComponent {

  countries$!: Country[];
  country!: Country;
  selectedCountry: boolean = false;
  selectedLeague: boolean = false;
  leagues$!: Response[];
  league!: Response;
  sportType: string = "FOOTBALL";
  teams$!: FootballTeam[];
  team!: FootballTeam;
  isFootball: boolean = true;

    constructor(private footballApiService: FootballApiService, private router: Router, private favoriteTeamService: FavoriteTeamService) { }

  ngOnInit(): void {
    this.footballApiService.getCountries()
      .then(response => {
        this.countries$ = response;
      })
  }

  onSelectSportType($event: any) {
    if ($event.target.value === "Futbol") {
      this.sportType = "FOOTBALL";
      this.isFootball = true;
    } else {
      this.sportType = "BASKETBALL";
      this.isFootball = false;
    }
  }

  onSelectCountry(country: Country) {
    this.country = country;
    this.selectedCountry = true;
    this.footballApiService.getLeaguesByCountryCode(this.country.code)
      .then(response => {
        this.leagues$ = response.response
      })
  }

  onSelectLeague(item: Response) {
      this.league = item;
      this.selectedLeague = true;
      this.footballApiService.getTeams(this.league.league.id, 2023)
        .then(response => {
          this.teams$ = response;
        })
  }

  onSelectTeam(team: FootballTeam) {
    this.team = team;
  }

  saveFavoriteTeam() {
    if (this.isFootball) {
      if (this.country == null || this.league == null || this.team == null) {
        this.errorAlert();
      } else {
        console.log(this.sportType)
        let userId = parseInt(window.localStorage.getItem("user_id")!);
        this.favoriteTeamService.saveFavoriteTeam(userId, this.team.team.id, this.sportType)
          .then(response => {
            this.successAlert();
          })
      }
    }
  }

  successAlert() {
    Swal.fire({
      title: "Başarılı",
      html: "Favorindeki takım bilgileri kaydedildi. Anasayfaya yönlendiriliyorsunuz...",
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
      html: "Tüm alanları seçiniz...",
      icon: 'error',
    });
  }
}
