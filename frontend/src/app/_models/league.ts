import {Country} from "./country";


export class LeagueResponse {
  response!: Response[];

}

export class Response {
  league!: League;
  country!: Country;
  seasons!: Season[];
}

export class League {
  id!: number;
  name!: string;
  type!: string;
  logo!: string;
}

export class Season {
  year!: number;
  start!: string;
  end!: string;
  current!: boolean;
  coverage!: Coverage;
}

export class Coverage {
  public fixtures!: Fixtures;
  public standings!: boolean;
  public players!: boolean;
  public top_scorers!: boolean;
  public top_assists!: boolean;
  public top_cards!: boolean;
  public injuries!: boolean;
  public predictions!: boolean;
  public odds!: boolean;
}

export class Fixtures {
  events!: boolean;
  lineups!: boolean;
  statistics_fixtures!: boolean;
  statistics_players!: boolean;
}
