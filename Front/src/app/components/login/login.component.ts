import { Component } from '@angular/core';
import { User } from '../user/User';
import { LoginService } from '../../services/logIn/login.service';
import { FormsModule } from '@angular/forms';

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
  constructor(private logInService: LoginService) {

  }
  login(): String {
    this.logInService.login(this.request).subscribe(response => {
      if (response) {
        console.log("Response received");
      }
      else {
        console.log("No response");
      }
    });
    console.log("User " + this.request.name + '|' + this.request.passwd);
    return "Error";
  }
}
