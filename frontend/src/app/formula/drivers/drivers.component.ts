import { Component } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {DriversResponse} from "../../_models/drivers";
import {Formula1ApiService} from "../../_services/formula1-api.service";

@Component({
  selector: 'app-drivers',
  templateUrl: './drivers.component.html',
  styleUrl: './drivers.component.css'
})
export class DriversComponent {

  driver!: DriversResponse

  constructor(private route: ActivatedRoute, private formula1Api: Formula1ApiService, private  router: Router) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.formula1Api.getDriversById(params['id'])
        .then(response => {
          this.driver = response.response[0];
        })
    })
  }

  teamDetail(id: number) {
    this.router.navigate(["/formula-team/" + id]);
  }
}
