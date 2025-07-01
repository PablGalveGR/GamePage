import { Component } from '@angular/core';
import { UserService } from '../../services/user/user.service';
import { User } from './User';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-user',
  standalone: true,
  imports: [NgFor],
  templateUrl: './user.component.html',
  styleUrl: './user.component.css'
})
export class UserComponent {
  constructor(private userService: UserService) { }
  users: User[] = [];
  ngOnInit() {
    this.getAllUsers();
  }
  getAllUsers() {
    this.userService.getUsernames().subscribe(users_ => this.users = users_);
    console.log("All users done");
  }
}
