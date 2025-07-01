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
  //users = USERS;
  getUsernames(): Observable<User[]> {
    return this.httpClient.get<User[]>('http://localhost:8080'+'/api/users');
  }
  /*getUsernames(): Observable<User[]> {
    return of(this.users);
  }*/
  getUser(id: number): Observable<User> {
    return this.httpClient.get<User>('http://localhost:8080'+'/api/users/'+id);
  }
  getName(id: number) :Observable<User>{
     return this.httpClient.get<User>('http://localhost:8080'+'/api/users/'+id);
  }
  
}
