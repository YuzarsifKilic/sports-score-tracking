import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { HeaderComponent } from './header/header.component';
import { NavbarComponent } from './navbar/navbar.component';
import { FootballHomeComponent } from './football/football-home/football-home.component';
import { FootballMatchDetailComponent } from './football/football-match-detail/football-match-detail.component';
import { FootballH2hComponent } from './football/football-h2h/football-h2h.component';
import { FootballMatchSummaryComponent } from './football/football-match-summary/football-match-summary.component';
import { FootballMatchStatisticsComponent } from './football/football-match-statistics/football-match-statistics.component';
import { FootballMatchCommentsComponent } from './football/football-match-comments/football-match-comments.component';
import { BasketballHomeComponent } from './basketball/basketball-home/basketball-home.component';
import { BasketballMatchDetailComponent } from './basketball/basketball-match-detail/basketball-match-detail.component';
import { BasketballH2hComponent } from './basketball/basketball-h2h/basketball-h2h.component';
import { BasketballMatchCommentsComponent } from './basketball/basketball-match-comments/basketball-match-comments.component';
import { BasketballMatchStatisticsComponent } from './basketball/basketball-match-statistics/basketball-match-statistics.component';
import { BasketballMatchSummaryComponent } from './basketball/basketball-match-summary/basketball-match-summary.component';
import { FormulaHomeComponent } from './formula/formula-home/formula-home.component';
import { SignUpComponent } from './login/sign-up/sign-up.component';
import { SignInComponent } from './login/sign-in/sign-in.component';
import { LoginPageComponent } from './login/login-page/login-page.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {ToastrModule} from "ngx-toastr";
import { FavoriteTeamComponent } from './favorite-team/favorite-team.component';
import {LazyLoadImageModule} from "ng-lazyload-image";
import { DriversComponent } from './formula/drivers/drivers.component';
import { BasketballPlayerComponent } from './basketball/basketball-player/basketball-player.component';
import { FormulaTeamComponent } from './formula/formula-team/formula-team.component';
import { FootballTeamComponent } from './football/football-team/football-team.component';
import { FootballTeamStandingsComponent } from './football/football-team/football-team-standings/football-team-standings.component';
import { FootballTeamFixtureComponent } from './football/football-team/football-team-fixture/football-team-fixture.component';
import { FootballTeamPlayersComponent } from './football/football-team/football-team-players/football-team-players.component';
import {NgIcon} from "@ng-icons/core";
import {MatIcon} from "@angular/material/icon";
import { FootballPlayerComponent } from './football/football-player/football-player.component';
import { UserFavoriteTeamComponent } from './favorite-team/user-favorite-team/user-favorite-team.component';
import { BasketballTeamComponent } from './basketball/basketball-team/basketball-team.component';
import { BasketballTeamStandingsComponent } from './basketball/basketball-team/basketball-team-standings/basketball-team-standings.component';
import { BasketballTeamPlayersComponent } from './basketball/basketball-team/basketball-team-players/basketball-team-players.component';
import { BasketballTeamGamesComponent } from './basketball/basketball-team/basketball-team-games/basketball-team-games.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    NavbarComponent,
    FootballHomeComponent,
    FootballMatchDetailComponent,
    FootballH2hComponent,
    FootballMatchSummaryComponent,
    FootballMatchStatisticsComponent,
    FootballMatchCommentsComponent,
    BasketballHomeComponent,
    BasketballMatchDetailComponent,
    BasketballH2hComponent,
    BasketballMatchCommentsComponent,
    BasketballMatchStatisticsComponent,
    BasketballMatchSummaryComponent,
    FormulaHomeComponent,
    SignUpComponent,
    SignInComponent,
    LoginPageComponent,
    FavoriteTeamComponent,
    DriversComponent,
    BasketballPlayerComponent,
    FormulaTeamComponent,
    FootballTeamComponent,
    FootballTeamStandingsComponent,
    FootballTeamFixtureComponent,
    FootballTeamPlayersComponent,
    FootballPlayerComponent,
    UserFavoriteTeamComponent,
    BasketballTeamComponent,
    BasketballTeamStandingsComponent,
    BasketballTeamPlayersComponent,
    BasketballTeamGamesComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    ToastrModule.forRoot(),
    FormsModule,
    LazyLoadImageModule,
    NgIcon,
    MatIcon
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
