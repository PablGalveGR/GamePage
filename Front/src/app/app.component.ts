import { Component } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { LoginService } from './services/logIn/login.service';
import { NgIf } from '@angular/common';
import { User } from './components/user/User';
import { SignUpService } from './services/signup/sign-up.service';
import { FormsModule } from '@angular/forms';
import { UserService } from './services/user/user.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NgIf, FormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  constructor(private router: Router, private userService: UserService, private loginService: LoginService, private signUpService: SignUpService) { }
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
    //this.checkSession();
  }
  goTo(url: string) {
    this.router.navigate([url]);
    //this.checkSession();
  }
  showFormLog() {
    this.loginForm = !this.loginForm;
    this.signUpForm = false;
    this.request.passwd = "";
  }
  showFormSign() {
    this.signUpForm = !this.signUpForm;
    this.loginForm = false;
    this.request.passwd = "";
  }
  checkForms(fields: User): boolean {
    return fields.name.length > 0 && fields.passwd.length > 0;
  }
  createUser(): string {
    let error: string = "";
    if (this.checkForms(this.user)) {
      this.signUpService.createUser(this.user).subscribe(user => {

      });
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
  login(){
    if(this.checkForms(this.request)){
      this.loginService.login(this.request).subscribe(response => {
        if(response.user.name == this.request.name){
          console.log("New session for :"+ response.user.name);
          
        }
      });
    }this.logged = true;
  }
  getUsername(): string {
    let id: number = parseInt(sessionStorage.key(0)!);
    let name: string = "";
    this.userService.getName(id).subscribe(user => { name = user.name });
    return name;
  }
  /*checkSession(): boolean  {
  /*this.login_ = false;
  if (sessionStorage.length > 0) {
    this.loginService.checkLogin().subscribe(session => {
      if (session.token != '' && session.token.length > 0) {
        if (parseInt(sessionStorage.key(0)!) == session.user.id) {
          sessionStorage.setItem(session.user.id.toString(), session.token);
          this.login_ = true;
          console.log("Session for " + session.user.id + " correct and updated");
        }
      }
      else {
        sessionStorage.clear();
        console.log("Session for " + session.user.id + " closed due to incorreect match");
        this.login_ = false;
      }
    });
  }
  return this.login_;
}*/

}

