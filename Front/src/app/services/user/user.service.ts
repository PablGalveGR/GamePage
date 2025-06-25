import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { User } from '../../components/user/User';
import { USERS } from '../../components/user/users';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor() { }
  users = USERS;
  getUsernames() : Observable<User[]>{
    return of(this.users);

  }
  getUsername(id:number) : Observable<User>{
    return of(this.users.find(user => user.id == id)!);
  }
}
