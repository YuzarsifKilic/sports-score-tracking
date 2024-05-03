export class Drivers {
  response!: DriversResponse[];
}

export class Country {
  name!: string;
  code!: string;
}

export class HighestRaceFinish {
  position!: number;
  number!: number;
}

export class Parameters {
  search!: string;
}

export class DriversResponse {
  id!: number;
  name!: string;
  abbr!: string;
  image!: string;
  nationality!: string;
  country!: Country;
  birthdate!: string;
  birthplace!: string;
  number!: number;
  grands_prix_entered!: number;
  world_championships!: number;
  podiums!: number;
  highest_race_finish!: HighestRaceFinish;
  highest_grid_position!: number;
  career_points!: string;
  teams!: Teams[];
}

export class Teams {
  season!: number;
  team!: Team;
}

export class Team {
  id!: number;
  name!: string;
  logo!: string;
}
