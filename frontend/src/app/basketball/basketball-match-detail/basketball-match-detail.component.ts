import {Component, Type} from '@angular/core';
import {FootballH2hComponent} from "../../football/football-h2h/football-h2h.component";
import {BasketballH2hComponent} from "../basketball-h2h/basketball-h2h.component";
import {ActivatedRoute, Router} from "@angular/router";
import {BasketballMatchStatisticsComponent} from "../basketball-match-statistics/basketball-match-statistics.component";
import {BasketballMatchCommentsComponent} from "../basketball-match-comments/basketball-match-comments.component";
import {BasketballMatchSummaryComponent} from "../basketball-match-summary/basketball-match-summary.component";
import {NbaApiService} from "../../_services/nba-api.service";
import {NbaGamesResponse} from "../../_models/nba-games";
import {FavoriteMatchService} from "../../_services/favorite-match.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-basketball-match-detail',
  templateUrl: './basketball-match-detail.component.html',
  styleUrl: './basketball-match-detail.component.css'
})
export class BasketballMatchDetailComponent {

  currentComponent: Type<any> | null = BasketballMatchSummaryComponent  ;
  matchId!: number;
  game!: NbaGamesResponse;
  isFavorite: boolean = false;

  constructor(private router: Router, private route: ActivatedRoute, private nbaApiService: NbaApiService, private favoriteMatchService: FavoriteMatchService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.matchId = params['matchId'];
    });
    this.nbaApiService.getMatchesById(this.matchId)
      .then(response => {
        this.game = response.response[0];
      })
    if (window.localStorage.getItem("user_id") != null) {
      this.favoriteMatchService.checkFavoriteMatch(parseInt(window.localStorage.getItem("user_id")!), +this.matchId, "BASKETBALL")
        .then(response => {
          this.isFavorite = response;
        })
    }
  }

  h2h() {
    this.currentComponent = BasketballH2hComponent;
  }

  matchSummary() {
    this.currentComponent = BasketballMatchSummaryComponent;
  }

  matchStatistics() {
    this.currentComponent = BasketballMatchStatisticsComponent;
  }

  matchComments() {
    this.currentComponent = BasketballMatchCommentsComponent;
  }

  teamPage(id: number) {
    this.router.navigate(["basketball-team/" + id]);
  }

  addFavorite() {
    if (window.localStorage.getItem("user_id") == null || window.localStorage.getItem("user_id") == "") {
      Swal.fire({
        title: "Giriş yapmanız gerekiyor!",
        html: "Maçı favorilere eklemek için giris yapmanız gerekiyor.",
        icon: 'warning',
        timer: 3000,
        timerProgressBar: true,
      }).then((result) => {
        if (result.dismiss === Swal.DismissReason.timer) {
          this.router.navigate(["football-home"]);
        }
      });
      this.router.navigate(["login"]);
      return;
    }
    this.favoriteMatchService.addFavoriteMatch(parseInt(window.localStorage.getItem("user_id")!), +this.matchId, "BASKETBALL")
      .then(response => {
        this.isFavorite = true;
        Swal.fire({
          title: "Maç favorilere eklendi!",
          icon: 'success',
          timer: 3000,
          timerProgressBar: true,
        });
      })
  }

  deleteFavorite() {
    Swal.fire({
      title: "Favori maçlardan cıkarılsın mı?",
      showDenyButton: true,
      showCancelButton: true,
      confirmButtonText: "Evet",
      denyButtonText: `Hayır`
    }).then((result) => {
      if (result.isConfirmed) {
        this.favoriteMatchService.deleteFavoriteMatch(parseInt(window.localStorage.getItem("user_id")!), +this.matchId, "BASKETBALL")
          .then(response => {
            Swal.fire("Saved!", "", "success");
          })
      } else if (result.isDenied) {
        Swal.fire("Changes are not saved", "", "info");
      }
    });
  }
}
