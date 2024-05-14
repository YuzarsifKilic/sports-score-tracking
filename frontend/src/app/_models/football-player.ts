export class PlayersResponse {
  paging!: Paging;
  response!: Response[];
}

export class Paging {
  current!: number;
  total!: number;
};

export class Birth {
  date!: string;
  place!: string;
  country!: string;
};

export class Cards {
  yellow!: number;
  yellowred!: number;
  red!: number;
};

class Dribbles {
  attempts!: number;
  success!: number;
  past!: number;
}

export class Duels {
  total!: number;
  won!: number;
};

export class Fouls {
  drawn!: number;
  committed!: number;
};

export class Games {
  appearances!: number;
  lineups!: number;
  minutes!: number;
  number!: any;
  position!: string;
  rating!: string;
  captain!: boolean;
};

export class Goals {
  total!: number;
  conceded!: number;
  assists!: number;
  saves!: number;
};

export class League {
  id!: number;
  name!: string;
  country!: string;
  logo!: string;
  flag!: string;
  season!: number;
};

export class Passes {
  total!: number;
  key!: number;
  accuracy!: number;
};

export class Penalty {
  won!: number;
  committed!: number;
  scored!: number;
  missed!: number;
  saved!: number;
};

export class Shots {
  total!: number;
  on!: number;
};

export class Substitutes {
  in!: number;
  out!: number;
  bench!: number;
};

export class Tackles {
  total!: number;
  blocks!: number;
  interceptions!: number;
};

export class Team {
  id!: number;
  name!: string;
  logo!: string;
};

export class Statistic {
  team!: Team;
  league!: League;
  games!: Games;
  substitutes!: Substitutes;
  shots!: Shots;
  goals!: Goals;
  passes!: Passes;
  tackles!: Tackles;
  duels!: Duels;
  dribbles!: Dribbles;
  fouls!: Fouls;
  cards!: Cards;
  penalty!: Penalty;
};

export class Player {
  id!: number;
  name!: string;
  firstname!: string;
  lastname!: string;
  age!: number;
  birth!: Birth;
  nationality!: string;
  height!: string;
  weight!: string;
  injured!: boolean;
  photo!: string;
};

export class Response {
  player!: Player;
  statistics!: Statistic[];
};
