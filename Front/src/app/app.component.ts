import { Component, Directive, HostListener } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { LoginService } from './services/logIn/login.service';
import { NgIf } from '@angular/common';
import { User } from './components/user/User';
import { FormsModule } from '@angular/forms';
import { SignupLoginComponent } from './components/signup-login/signup-login.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NgIf, FormsModule, SignupLoginComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  constructor(private router: Router,
    private loginService: LoginService
  ) { }
  title = 'GeekyVerse';
  logged: boolean = false;
  signUpForm: boolean = false;
  loginForm: boolean = false;
  request: User = {
    name: '',
    passwd: '',
    id: 0
  };
  user: User = {
    name: '',
    passwd: '',
    id: 0
  };
  ngOnInit() {
    this.logout();
  }
  ngOnDestroy() {
    this.logout();
  }
  goTo(url: string) {
    this.router.navigate([url]);
    this.checkSession();
  }
  logout() {
    if (sessionStorage.length > 0) {
      this.loginService.logout().subscribe(session => {
        this.logged = false;
        this.request.name = "";
        sessionStorage.clear();
      });
    }
  }
  checkSession(): boolean {
    if (sessionStorage.length > 0) {
      this.loginService.checkLogin().subscribe(session => {
        if (session.token != '' || session.token.length > 0) {
          if (sessionStorage.key(0)! == session.user.name) {
            sessionStorage.setItem(session.user.id.toString(), session.token);
            this.logged = true;
            console.log("Session for " + session.user.id + " correct and updated");
          }
        }
        else {
          this.logout();
          console.log("Session for " + session.user.id + " closed due to incorreect match");
        }
      });
    }
    return this.logged;
  }

}

