import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-football-match-statistics',
  templateUrl: './football-match-statistics.component.html',
  styleUrl: './football-match-statistics.component.css'
})
export class FootballMatchStatisticsComponent {

  @Input() teamAGoals: number = 6;
  @Input() teamBGoals: number = 4;
  @Input() teamAStats: number = 50; // Takım A'nın yüzdesi
  @Input() teamBStats: number = 50; // Takım B'nın yüzdesi
  height1 =  200;
  height2 =  120;

}
