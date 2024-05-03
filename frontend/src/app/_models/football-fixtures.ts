export class FootballFixtures {
  response!: FootballFixturesResponse[];
}

export class Away {
  id!: number;
  name!: string;
  logo!: string;
  winner!: boolean;
}

export class Extratime {
  home!: number;
  away!: number;
}

export class Fixture {
  id!: number;
  referee!: string;
  timezone!: string;
  date!: string;
  timestamp!: number;
  periods!: Periods;
  venue!: Venue;
  status!: Status;
}

export class Fulltime {
  home!: number;
  away!: number;
}

export class Goals {
  home!: number;
  away!: number;
}

export class Halftime {
  home!: number;
  away!: number;
}

export class Home {
  id!: number;
  name!: string;
  logo!: string;
  winner!: boolean;
}

export class League {
  id!: number;
  name!: string;
  country!: string;
  logo!: string;
  flag!: string;
  season!: number;
  round!: string;
}

export class Penalty {
  home!: number;
  away!: number;
}

export class Periods {
  first!: number;
  second!: number;
}

export class FootballFixturesResponse {
  league!: League;
  response!: FootballFixturesCustomResponse[];
}

export class FootballFixturesCustomResponse {
  fixture!: Fixture;
  league!: League;
  teams!: Teams;
  goals!: Goals;
  score!: Score;
}

export class Score {
  halftime!: Halftime;
  fulltime!: Fulltime;
  extratime!: Extratime;
  penalty!: Penalty;
}

export class Status {
  long!: string;
  short!: string;
  elapsed!: number;
}

export class Teams {
  home!: Home;
  away!: Away;
}

export class Venue {
  id!: number;
  name!: string;
  city!: string;
}



