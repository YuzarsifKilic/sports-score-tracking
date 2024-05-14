export class FootballTeam {
  team!: Team;
  venue!: Venue;
}

export class Team {
  id!: number;
  name!: string;
  code!: string;
  country!: string;
  founded!: number;
  national!: boolean;
  logo!: string;
}

export class Venue {
  id!: number;
  name!: string;
  address!: string;
  city!: string;
  capacity!: number;
  surface!: string;
  image!: string;
}
