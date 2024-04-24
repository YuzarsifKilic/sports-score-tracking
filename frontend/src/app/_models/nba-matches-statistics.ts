export class NbaMatchesStatistics {
  response!: NbaMatchesStatisticsResponse[];
}

export class NbaMatchesStatisticsResponse {
  player!: Player;
  team!: Team;
  game!: Game;
  points!: number;
  pos!: string;
  min!: string;
  fgm!: number;
  fga!: number;
  fgp!: string;
  ftm!: number;
  fta!: number;
  ftp!: string;
  tpm!: number;
  tpa!: number;
  tpp!: string;
  offReb!: number;
  defReb!: number;
  totReb!: number;
  assists!: number;
  pFouls!: number;
  steals!: number;
  turnovers!: number;
  blocks!: number;
  plusMinus!: string;
  comment!: string;
}

export class Player {
  id!: number;
  firstname!: string;
  lastname!: string;
}

export class Game {
  id!: number;
}

export class Team {
  id!: number;
  name!: string;
  nickname!: string;
  code!: string;
  logo!: string;
}
