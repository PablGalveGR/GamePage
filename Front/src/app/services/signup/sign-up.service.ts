import { Injectable } from '@angular/core';
import { User } from '../../components/user/User';
import { HttpClient } from '@angular/common/http';
import { ROUTE } from '../ROUTE';
import * as CryptoJS from 'crypto-js';

@Injectable({
  providedIn: 'root'
})
export class SignUpService {

  constructor(private httpClient: HttpClient) { }
  createUser(user: User) {
    user.passwd = CryptoJS.SHA256(user.passwd).toString();
    console.log("Pasw: " + user.passwd);
    this.httpClient.post<User>(ROUTE + '/api/users', user).subscribe(user => console.log('AWAWAWAW'));
  }
}
