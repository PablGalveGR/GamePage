import { Component } from '@angular/core';
import { User } from '../user/User';
import { UserService } from '../../services/user/user.service';
import { LoginService } from '../../services/logIn/login.service';
import { SignUpService } from '../../services/signup/sign-up.service';
import { NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import * as CryptoJS from 'crypto-js';

@Component({
  selector: 'app-signup-login',
  standalone: true,
  imports: [NgIf, FormsModule],
  templateUrl: './signup-login.component.html',
  styleUrl: './signup-login.component.css'
})
export class SignupLoginComponent {
  constructor(private userService: UserService,
    private loginService: LoginService,
    private signUpService: SignUpService) { }
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
  showFormLog() {
    this.loginForm = !this.loginForm;
    if (this.loginForm) {
      this.signUpForm = false;
    }
    this.request.passwd = "";
  }
  showFormSign() {
    this.signUpForm = !this.signUpForm;
    if (this.signUpForm) {
      this.loginForm = false;
    }
    this.request.passwd = "";
  }
  checkForms(fields: User): boolean {
    return fields.name.length > 0 && fields.passwd.length > 0;
  }
  createUser(): string {
    let error: string = "";
    if (this.checkForms(this.user)) {
      this.user.passwd = CryptoJS.SHA256(this.user.passwd).toString();
      this.signUpService.createUser(this.user);
      console.log("User sent " + this.user.name + '|' + this.user.passwd);
      this.user.name = "";
      this.user.passwd = "";
      this.showFormSign();
    }
    else {
      error = "Password or username missing";
    }
    return error;

  }
  login() {
    if (this.request.name.length > 0 && this.request.passwd.length > 0) {
      this.request.passwd = this.request.passwd;//encrypt
      this.loginService.login(this.request).subscribe(response => {
        if (response) {
          console.log(response.token);
          if (response.user && response.user.name == this.request.name) {
            //Save the session into the client server
            sessionStorage.setItem(response.user.name, response.token);
            console.log("Session initialized :" + response.user.name);
            this.logged = true;
          }
        }
        else {
          console.log("No response");
        }
      });
    }
    console.log("User " + this.request.name + '|' + this.request.passwd);
    this.showFormLog();
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
    //this.logged = false;
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
