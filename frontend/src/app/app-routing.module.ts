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

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'football-home', component: FootballHomeComponent },
  { path: 'football-match-detail/:matchId', component: FootballMatchDetailComponent },
  { path: 'football-match-detail/h2h', component: FootballH2hComponent },
  { path: 'basketball-home', component: BasketballHomeComponent },
  { path: 'basketball-match-detail/:matchId', component: BasketballMatchDetailComponent },
  { path: 'formula-home', component: FormulaHomeComponent },
  { path: 'login', component: LoginPageComponent },
  { path: 'add-favorite-team', component: FavoriteTeamComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
