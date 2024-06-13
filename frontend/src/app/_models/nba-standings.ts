export class NbaStandings {
  response!: Response[];
}

class Conference {
  name!: string;
  rank!: number;
  win!: number;
  loss!: number;
}

class Division {
  name!: string;
  rank!: number;
  win!: number;
  loss!: number;
  gamesBehind!: string;
}

class Loss {
  home!: number;
  away!: number;
  total!: number;
  percentage!: string;
  lastTen!: number;
}

class Parameters {
  season!: string;
  league!: string;
}

class Response {
  league!: string;
  season!: number;
  team!: Team;
  conference!: Conference;
  division!: Division;
  win!: Win;
  loss!: Loss;
  gamesBehind!: string;
  streak!: number;
  winStreak!: boolean;
  tieBreakerPoints!: any;
}

class Team {
  id!: number;
  name!: string;
  nickname!: string;
  code!: string;
  logo!: string;
}

class Win {
  home!: number;
  away!: number;
  total!: number;
  percentage!: string;
  lastTen!: number;
}
