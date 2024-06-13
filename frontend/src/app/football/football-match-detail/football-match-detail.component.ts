import {Component, Type} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {FootballH2hComponent} from "../football-h2h/football-h2h.component";
import {FootballMatchSummaryComponent} from "../football-match-summary/football-match-summary.component";
import {FootballMatchStatisticsComponent} from "../football-match-statistics/football-match-statistics.component";
import {FootballMatchCommentsComponent} from "../football-match-comments/football-match-comments.component";
import {FootballApiService} from "../../_services/football-api.service";
import {FootballFixturesCustomResponse} from "../../_models/football-fixtures";
import {FavoriteMatchService} from "../../_services/favorite-match.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-football-match-detail',
  templateUrl: './football-match-detail.component.html',
  styleUrl: './football-match-detail.component.css'
})
export class FootballMatchDetailComponent {

  currentComponent: Type<any> | null = FootballH2hComponent;
  fixtureDetail!: FootballFixturesCustomResponse;
  isFavorite: boolean = false;

  constructor(private router: Router,
              private footballApiService: FootballApiService,
              private route: ActivatedRoute,
              private favoriteMatchService: FavoriteMatchService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.footballApiService.getFixturesById(params['matchId'])
        .then(response => {
          this.fixtureDetail = response;
          if (window.localStorage.getItem("user_id") != null && window.localStorage.getItem("user_id") != "") {
            this.favoriteMatchService.checkFavoriteMatch(parseInt(window.localStorage.getItem("user_id")!), this.fixtureDetail.fixture.id, "FOOTBALL")
              .then(response => {
                this.isFavorite = response;
              })
          }
        })
    })
  }

  h2h() {
    this.currentComponent = FootballH2hComponent;
  }

  matchSummary() {
    this.currentComponent = FootballMatchSummaryComponent;
  }

  matchStatistics() {
    this.currentComponent = FootballMatchStatisticsComponent;
  }

  matchComments() {
    this.currentComponent = FootballMatchCommentsComponent;
  }

  teamPage(id: number) {
    this.router.navigate(["football-team/" + id]);
  }

  addFavorite(id: number) {
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
    this.favoriteMatchService.addFavoriteMatch(parseInt(window.localStorage.getItem("user_id")!), id, "FOOTBALL")
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
        this.favoriteMatchService.deleteFavoriteMatch(parseInt(window.localStorage.getItem("user_id")!), this.fixtureDetail.fixture.id, "FOOTBALL")
          .then(response => {
            Swal.fire("Saved!", "", "success");
          })
      } else if (result.isDenied) {
        Swal.fire("Changes are not saved", "", "info");
      }
    });
  }
}
