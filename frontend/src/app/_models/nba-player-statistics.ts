export class NbaPlayerStatistics {
   player!: Player;
   team!: Team;
   game!: Game;
   points!: number;
   pos!: Set<string>;
   min!: string;
   offReb!: number;
   defReb!: number;
   totReb!: number;
   assists!: number;
   steals!: number;
   blocks!: number;
}

export class Team {
   id!: number;
   name!: string;
   nickname!: string;
   code!: string;
   logo!: string;
}

export class Game {
   id!: number;
}

export class Player {
   id!: number;
   firstname!: string;
   lastname!: string;
}
