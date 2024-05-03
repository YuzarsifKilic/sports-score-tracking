export class LineUpsResponse {
  response!: Response[];
}

export class Coach {
  id!: number;
  name!: string;
  photo!: string;
}

export class Colors {
  player!: Player;
  goalkeeper!: Goalkeeper;
}

export class Goalkeeper {
  primary!: string;
  number!: string;
  border!: string;
}

export class Player {
  primary!: string;
  number!: string;
  border!: string;
  id!: number;
  name!: string;
  pos!: string;
  grid!: string;
}

export class Response {
  team!: Team;
  coach!: Coach;
  formation!: string;
  startXI!: StartXI[];
  substitutes!: Substitute[];
}

export class StartXI {
  player!: Player;
}

export class Substitute {
  player!: Player;
}

export class Team {
  id!: number;
  name!: string;
  logo!: string;
  colors!: Colors;
}

