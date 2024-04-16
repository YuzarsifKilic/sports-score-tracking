import { Component } from '@angular/core';

@Component({
  selector: 'app-formula-home',
  templateUrl: './formula-home.component.html',
  styleUrl: './formula-home.component.css'
})
export class FormulaHomeComponent {

  openDetails: number | null = null;

  // Bu fonksiyon, belirli bir detay kutusunu açıp kapatmak için kullanılacak
  events = [
    {},{}, {}, {}, {}, {}
  ]
  toggleDetails(index: number): void {
    this.openDetails = this.openDetails === index ? null : index;
  }

  matchDetails() {

  }
}
