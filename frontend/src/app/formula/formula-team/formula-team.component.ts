import { Component } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Formula1ApiService} from "../../_services/formula1-api.service";
import {FormulaTeam} from "../../_models/formula-team";

@Component({
  selector: 'app-formula-team',
  templateUrl: './formula-team.component.html',
  styleUrl: './formula-team.component.css'
})
export class FormulaTeamComponent {

  team!: FormulaTeam;

  constructor(private route: ActivatedRoute, private formula1Api: Formula1ApiService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.formula1Api.getTeamById(params['id'])
        .then(response => {
          this.team = response;
        })
    })
  }
}
