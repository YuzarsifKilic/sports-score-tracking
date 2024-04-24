export class Race {
  response!: RaceResponse[];
}

export class RaceResponse {
  id!: number;
  competition!: Competition;
  circuit!: Circuit;
  season!: number;
  type!: string;
  laps!: Laps;
  fastest_lap!: FastestLap;
  distance!: string;
  timezone!: string;
  date!: string;
  weather!: any;
  status!: string;
}

export class Circuit {
  id!: number;
  name!: string;
  image!: string;
}

export class Competition {
  id!: number;
  name!: string;
  location!: Location
}

export class Driver {
  id!: number;
}

export class FastestLap {
  driver!: Driver;
  time!: string;
}

export class Laps {
  current!: {};
  total!: number;
}

export class Location {
  country!: string;
  city!: string;
}
