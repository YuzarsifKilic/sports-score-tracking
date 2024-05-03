export class NbaGames {

  response!: NbaGamesResponse[];
}

export class NbaGamesResponse {
  id!: number;
  league!: string;
  season!: number;
  date!: Date;
  stage!: number;
  status!: Status;
  periods!: Periods;
  arena!: Arena;
  teams!: Teams;
  scores!: Scores;
  officials!: string[];
  timesTied!: number;
  leadChanges!: number;
  nugget!: any;
}

export class Arena {
  name!: string;
  city!: string;
  state!: string;
  country!: string;
}

export class Date {
  start!: string;
  end!: string;
  duration!: string;
}

export class Home {
  id!: number;
  name!: string;
  nickname!: string;
  code!: string;
  logo!: string;
  win!: number;
  loss!: number;
  series!: Series;
  linescore!: string[];
  points!: number;
}

export class Periods {
  current!: number;
  total!: number;
  endOfPeriod!: boolean;
}

export class Scores {
  visitors!: Visitors;
  home!: Home;
}

export class Series {
  win!: number;
  loss!: number;
}


export class Status {
  clock!: any;
  halftime!: boolean;
  short!: number;
  long!: string;
}
 export class Teams {
  visitors!: Visitors;
  home!: Home;
}

export class Visitors {
  id!: number;
  name!: string;
  nickname!: string;
  code!: string;
  logo!: string;
  win!: number;
  loss!: number;
  series!: Series;
  linescore!: string[];
  points!: number;
}


