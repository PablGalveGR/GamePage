import { Component } from '@angular/core';
import { User } from '../user/User';
import { LoginService } from '../../services/logIn/login.service';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  request: User = {
    name: '',
    passwd: '',
    id: 0
  };
  constructor(private logInService: LoginService, private router: Router) {

  }
  login(): String {
    this.logInService.login(this.request).subscribe(response => {
      if (response) {
        console.log(response.token);
        if(response.user && response.user.name == this.request.name){
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
    this.router.navigate(['/']);
    return "Error";
  }
}
