import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {FootballHomeComponent} from "./football/football-home/football-home.component";
import {FootballMatchDetailComponent} from "./football/football-match-detail/football-match-detail.component";
import {FootballH2hComponent} from "./football/football-h2h/football-h2h.component";
import {BasketballHomeComponent} from "./basketball/basketball-home/basketball-home.component";
import {BasketballMatchDetailComponent} from "./basketball/basketball-match-detail/basketball-match-detail.component";
import {FormulaHomeComponent} from "./formula/formula-home/formula-home.component";
import {LoginPageComponent} from "./login/login-page/login-page.component";
import {FavoriteTeamComponent} from "./favorite-team/favorite-team.component";
import {DriversComponent} from "./formula/drivers/drivers.component";
import {BasketballPlayerComponent} from "./basketball/basketball-player/basketball-player.component";
import {FormulaTeamComponent} from "./formula/formula-team/formula-team.component";
import {FootballTeamComponent} from "./football/football-team/football-team.component";
import {FootballPlayerComponent} from "./football/football-player/football-player.component";
import {UserFavoriteTeamComponent} from "./favorite-team/user-favorite-team/user-favorite-team.component";
import {BasketballTeamComponent} from "./basketball/basketball-team/basketball-team.component";

const routes: Routes = [
  { path: '', redirectTo: 'football-home', pathMatch: 'full' },
  { path: 'football-home', component: FootballHomeComponent },
  { path: 'football-match-detail/:matchId', component: FootballMatchDetailComponent },
  { path: 'football-match-detail/h2h', component: FootballH2hComponent },
  { path: 'football-team/:teamId', component: FootballTeamComponent },
  { path: 'football-player/:playerId', component: FootballPlayerComponent },
  { path: 'basketball-home', component: BasketballHomeComponent },
  { path: 'basketball-match-detail/:matchId', component: BasketballMatchDetailComponent },
  { path: 'basketball-player/:playerId', component: BasketballPlayerComponent },
  { path: 'basketball-team/:teamId', component: BasketballTeamComponent },
  { path: 'formula-home', component: FormulaHomeComponent },
  { path: 'drivers/:id', component: DriversComponent },
  { path: 'formula-team/:id', component: FormulaTeamComponent },
  { path: 'login', component: LoginPageComponent },
  { path: 'add-favorite-team', component: FavoriteTeamComponent },
  { path: 'favorite-teams', component: UserFavoriteTeamComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
