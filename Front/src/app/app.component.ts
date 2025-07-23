import { Component } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { LoginService } from './services/logIn/login.service';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NgIf],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  constructor(private router: Router, private loginService: LoginService) { }
  title = 'Front';
  login: boolean = false;
  ngOnInit() {
    //this.checkSession();
  }
  goTo(url: string) {
    this.router.navigate([url]);
    this.checkSession();
  }
  logout() {
    if(sessionStorage.length > 0){
      this.loginService.logout().subscribe(session => {
        this.login = false;
        sessionStorage.clear();
      });
    }
  }
  checkSession(): boolean {
    this.login = false;
    if (sessionStorage.length > 0) {
      this.loginService.checkLogin().subscribe(session => {
        if (sessionStorage.key(0) == session.user.name) {
          sessionStorage.setItem(session.user.name, session.token);
          this.login = true;
          console.log("Session for " + session.user.name + " correct and updated");
        }
      });
    }
    return this.login;
  }
}

