import { Component, NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { User } from '../user/User';
import { SignUpService } from '../../services/signup/sign-up.service';

@Component({
  selector: 'app-sign-up',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.css'
})
export class SignUpComponent {
  user: User = {
    id: 0,
    name: '',
    passwd: ''
  };
  constructor( private signUpService : SignUpService){

  }
  createUser(): String {
    this.signUpService.createUser(this.user);
    console.log("User sent " + this.user.name + '|' + this.user.passwd);
    return "Error";
  }

}
