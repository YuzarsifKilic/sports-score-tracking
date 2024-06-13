import { Component } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {NbaApiService} from "../../_services/nba-api.service";
import {FootballApiService} from "../../_services/football-api.service";
import {FavoriteTeamService} from "../../_services/favorite-team.service";
import {FavoriteTeam} from "../../_models/favorite-team";
import {FootballTeam} from "../../_models/football-team";
import {Response} from "../../_models/nba-team";
import Swal from "sweetalert2";

@Component({
  selector: 'app-user-favorite-team',
  templateUrl: './user-favorite-team.component.html',
  styleUrl: './user-favorite-team.component.css'
})
export class UserFavoriteTeamComponent {

  favoriteTeams!: FavoriteTeam[];
  footballTeams: FootballTeam[] = [];
  basketballTeams: Response[] = [];

  constructor(private router: Router,
              private route: ActivatedRoute,
              private nbaApiService: NbaApiService,
              private footballApiService: FootballApiService,
              private favoriteTeamService: FavoriteTeamService) { }

  ngOnInit(): void {
    this.favoriteTeamService.getFavoriteTeams(window.localStorage.getItem("user_id")!)
      .then(response => {
        this.favoriteTeams = response;
        this.favoriteTeams.map(item => {
          if (item.sportType === "FOOTBALL") {
            this.footballApiService.getTeamById(item.teamId)
              .then(response => {
                this.footballTeams.push(response);
              })
          } else if (item.sportType === "BASKETBALL") {
            this.nbaApiService.getTeamById(item.teamId)
              .then(response => {
                this.basketballTeams.push(response.response[0]);
              })
          }
        })
      })
  }

  footballTeamPage(id: number) {
    this.router.navigate(['football-team/' + id]);
  }

  deleteFavoriteTeam(id: number, football: string) {
    Swal.fire({
      title: "Takımı kaldırmak istediğinize emin misiniz?",
      showDenyButton: true,
      showCancelButton: true,
      confirmButtonText: "Kaldır",
      denyButtonText: `Kaldırma`
    }).then((result) => {
      /* Read more about isConfirmed, isDenied below */
      if (result.isConfirmed) {
        this.favoriteTeamService.deleteFavoriteTeam(parseInt(window.localStorage.getItem("user_id")!), id, football)
          .then(response => {
            Swal.fire("Takım Kaldırıldı", "", "success");
          })
      } else if (result.isDenied) {
        Swal.fire("Takım Kaldırılmadı", "", "info");
      }
    });
  }

  addFavoriteTeam() {
    this.router.navigate(['add-favorite-team']);
  }
}
