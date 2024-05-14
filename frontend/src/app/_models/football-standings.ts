export class FootballStandingsResponse {
  response!: Response[];
}

export class Response {
  league!: League;
}

export class League {
  id!: number;
  name!: string;
  country!: string;
  logo!: string;
  flag!: string;
  season!: number;
  standings!: FootballStandings[][];
}

export class FootballStandings {
  rank!: number;
  team!: Team;
  points!: number;
  goalsDiff!: number;
  group!: string;
  form!: string;
  status!: string;
  description!: string;
  all!: All;
  home!: Home;
  away!: Away;
  update!: string;
}

export class Team {
  id!: number;
  name!: string;
  logo!: string;
}

export class All {
  played!: number;
  win!: number;
  draw!: number;
  lose!: number;
  goals!: Goals;
}

export class Away {
  played!: number;
  win!: number;
  draw!: number;
  lose!: number;
  goals!: Goals;
}

export class Goals {
  for!: number;
  against!: number;
}

export class Home {
  played!: number;
  win!: number;
  draw!: number;
  lose!: number;
  goals!: Goals;
}

