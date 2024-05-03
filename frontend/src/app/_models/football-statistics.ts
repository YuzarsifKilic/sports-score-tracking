export class StatisticResponse {
  response!: Response[];
}

export class Response {
  team!: Team;
  statistics!: Statistic[];
}

export class Statistic {
  type!: string;
  value: any;
}
export class Team {
  id!: number;
  name!: string;
  logo!: string;
}
