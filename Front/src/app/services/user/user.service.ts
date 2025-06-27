import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { User } from '../../components/user/User';
import { USERS } from '../../components/user/users';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient) { }
  users = USERS;
  getUsernames(): Observable<User[]> {
    return of(this.users);
  }
  getUsername(id: number): Observable<User> {
    return of(this.users.find(user => user.id == id)!);
  }
  getUsernamesFromBack(): Observable<User[]> {
    return this.httpClient.get<User[]>('http://localhost:8080' + +'/api/users');
  }
}
