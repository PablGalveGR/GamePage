import { Injectable } from '@angular/core';
import { User } from '../../components/user/User';
import { HttpClient } from '@angular/common/http';
import { ROUTE } from '../ROUTE';

@Injectable({
  providedIn: 'root'
})
export class SignUpService {

  constructor(private httpClient: HttpClient) { }
  createUser(user: User) {
    this.httpClient.post<User>(ROUTE + '/api/users', user).subscribe(user => console.log('AWAWAWAW'));
  }
}
