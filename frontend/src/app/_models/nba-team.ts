export class TeamResponse {
  response!: Response[];
}

export class Africa {
  conference!: any;
  division!: any;
}

export class Leagues {
  standard!: Standard;
  vegas!: Vegas;
  utah!: Utah;
  sacramento!: Sacramento;
  africa!: Africa;
}

export class Response {
  id!: number;
  name!: string;
  nickname!: string;
  code!: string;
  city!: string;
  logo!: string;
  allStar!: boolean;
  nbaFranchise!: boolean;
  leagues!: Leagues;
}


export class Sacramento {
  conference!: string;
  division!: string;
}

export class Standard {
  conference!: string;
  division!: string;
}

export class Utah {
  conference!: string;
  division!: string;
}

export class Vegas {
  conference!: string;
  division!: string;
}
