import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { ROUTE } from '../ROUTE';
import { User } from '../../components/user/User';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private httpClient : HttpClient) {

  }
  login(request : User) : Observable<User> {
    return this.httpClient.post<User>(ROUTE + '/api/login', request);
  }
}
