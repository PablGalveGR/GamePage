import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { User } from '../../components/user/User';
import { HttpClient } from '@angular/common/http';
import { ROUTE } from '../ROUTE';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient) { }
  //users = USERS;
  getUsernames(): Observable<User[]> {
    return this.httpClient.get<User[]>(ROUTE + '/api/users');
  }
  getUserbyName(name : string) : Observable<number>{
    return this.httpClient.get<number>(ROUTE + "/api/users/name/" + name);
  }
  getUser(id: number): Observable<User> {
    return this.httpClient.get<User>(ROUTE + '/api/users/' + id);
  }
  getName(id: number): Observable<User> {
    return this.httpClient.get<User>(ROUTE + '/api/users/' + id);
  }

}
