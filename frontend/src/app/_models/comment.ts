export class Comment {
  id!: number;
  matchId!: number;
  content!: string;
  footballFan!: FootballFan;
  likeCount!: number;
  date!: string;
}

export class FootballFan {
  id!: number;
  email!: string;
  firstName!: string;
  lastName!: string;
}
