export class NbaPlayer {
  id!: number;
  firstname!: string;
  lastname!: string;
  birth!: Birth;
  nba!: NBA;
  height!: Height;
  weight!: Weight;
  college!: string;
  affiliation!: string;
  leagues!: Leagues;
}

export class Birth {
  date!: string;
  country!: string;
}

export class NBA {
  start!: number;
  pro!: number;
}

export class Height {
  feets!: string;
  inches!: string;
  meters!: number;
}

export class Weight {
  pounds!: number;
  kilograms!: number;
}

export class Leagues {
  standard!: Standard;
}

export class Standard {
  jersey!: number;
  active!: boolean;
  pos!: string;
}
