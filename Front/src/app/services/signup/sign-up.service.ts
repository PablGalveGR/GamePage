import { Injectable } from '@angular/core';
import { User } from '../../components/user/User';
import { HttpClient } from '@angular/common/http';
import { ROUTE } from '../ROUTE';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SignUpService {

  constructor(private httpClient: HttpClient) { }
  createUser(user: User) : Observable<User>{
    return this.httpClient.post<User>(ROUTE + '/api/users', user);
  }
}
