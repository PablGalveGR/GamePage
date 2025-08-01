import { Component, Directive, HostListener } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { LoginService } from './services/logIn/login.service';
import { NgIf } from '@angular/common';
import { User } from './components/user/User';
import { SignUpService } from './services/signup/sign-up.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NgIf, FormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  constructor(private router: Router, private loginService: LoginService, private signUpService: SignUpService) { }
  title = 'Front';
  login_: boolean = false;
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
    this.checkSession();

  }
  goTo(url: string) {
    this.router.navigate([url]);
    this.checkSession();
  }
  showFormLog() {
    this.loginForm = !this.loginForm;
    this.request.passwd = "";
  }
  showFormSign() {
    this.signUpForm = !this.signUpForm;
    this.request.passwd = "";
  }
  createUser(): String {
    this.signUpService.createUser(this.request);
    console.log("User sent " + this.request.name + '|' + this.request.passwd);
    this.showFormSign();
    return "Error";
  }
  login() {
    this.loginService.login(this.request).subscribe(response => {
      if (response) {
        console.log(response.token);
        if (response.user && response.user.name == this.request.name) {
          //Save the session into the client server
          sessionStorage.setItem(response.user.name, response.token);
          console.log("Session initialized :" + response.user.name);
        }
      }
      else {
        console.log("No response");
      }
    });
    console.log("User " + this.request.name + '|' + this.request.passwd);
    this.login_ = true;
    this.showFormLog();

  }
  logout() {
    if (sessionStorage.length > 0) {
      this.loginService.logout().subscribe(session => {
        this.login_ = false;
        sessionStorage.clear();
      });
    }
  }
  checkSession(): boolean {
    this.login_ = false;
    if (sessionStorage.length > 0) {
      this.loginService.checkLogin().subscribe(session => {
        if (sessionStorage.key(0) == session.user.name) {
          sessionStorage.setItem(session.user.name, session.token);
          this.login_ = true;
          console.log("Session for " + session.user.name + " correct and updated");
        }
      });
    }
    return this.login_;
  }
  getUsername(): string {
    let name: string = sessionStorage.key(0)!;
    return name;
  }
}

