export class Rankings {
  response!: RankingsResponse[];
}

export class RankingsResponse {
  race!: Race;
  driver!: Driver;
  team!: Team;
  position!: number;
  time!: string;
  laps!: number;
  grid!: string;
  pits!: number;
  gap!: any;
}

export class Driver {
  id!: number;
  name!: string;
  abbr!: string;
  number!: number;
  image!: string;
}

export class Race {
  id!: number;
}

export class Team {
  id!: number;
  name!: string;
  logo!: string;
}
