import {Component, Renderer2} from '@angular/core';
declare var widgets: any;
@Component({
  selector: 'app-football-match-summary',
  templateUrl: './football-match-summary.component.html',
  styleUrl: './football-match-summary.component.css'
})
export class FootballMatchSummaryComponent {

  constructor(private renderer: Renderer2) { }

  ngOnInit() {
    // Yeni bir script elementi oluşturun.
    const script = this.renderer.createElement('script');
    script.type = 'module'; // Modül tipini belirtin.
    script.src = 'https://widgets.api-sports.io/2.0.3/widgets.js'; // Script'in kaynağını belirtin.
    script.onload = () => {
      // Script yüklendikten sonra yapılacak işlemler.
      console.log('API Sports widget script loaded successfully');
    };
    // Oluşturulan script elementini document'in head etiketine ekleyin.
    this.renderer.appendChild(document.head, script);
  }

}
