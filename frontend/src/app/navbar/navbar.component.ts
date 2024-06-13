import {Component, ElementRef, HostListener, ViewChild} from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from "../_services/auth.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

  searchImage: String = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fuxwing.com%2Fsearch-icon%2F&psig=AOvVaw0OI79SVqhKZeGuyaMqC3lQ&ust=1712935413319000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCNi74vC7uoUDFQAAAAAdAAAAABAE";
  loggedIn: boolean = false;
  username!: string;
  constructor(private router: Router, private authService: AuthService) { }

  ngOnInit(): void {
    this.authService.loggedIn$.subscribe(loggedIn => {
      this.loggedIn = loggedIn;
    });
    this.authService.username$.subscribe(username => {
      this.username = username;
    })
  }

  footballHome() {
    this.router.navigate(['football-home']);
  }

  basketballHome() {
    this.router.navigate(["basketball-home"]);
  }

  formulaHome() {
    this.router.navigate(["formula-home"]);
  }

  loginPage() {
    this.router.navigate(["login"]);
  }

  @ViewChild('dropdownContainer') dropdownContainer!: ElementRef;
  isOpen = false;

  toggleDropdown() {
    this.isOpen = !this.isOpen;
  }

  @HostListener('document:click', ['$event'])
  closeDropdown(event: Event) {
    if (!this.dropdownContainer.nativeElement.contains(event.target)) {
      this.isOpen = false;
    }
  }

  logout() {
    this.authService.logout();
    Swal.fire({
      title: "Çıkış Yapıldı",
      html: "Çıkış Yapıldı anasayfaya yönlendiriliyosunuz...",
      icon: 'success',
      timer: 3000,
      timerProgressBar: true,
    }).then((result) => {
      if (result.dismiss === Swal.DismissReason.timer) {
        this.router.navigate(["football-home"]);
      }
    });
  }

  favoriteTeams() {
    this.router.navigate(["favorite-teams"]);
  }
}
