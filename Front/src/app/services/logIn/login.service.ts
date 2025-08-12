import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { ROUTE } from '../ROUTE';
import { User } from '../../components/user/User';
import { Session } from '../../components/session/Session';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private httpClient: HttpClient) {

  }
  login(request: User): Observable<Session> {
    return this.httpClient.post<Session>(ROUTE + '/api/session', request);
  }
  logout(): Observable<Session>{
    let session: Session = {
      user: {
        id: 0,
        name: '',
        passwd: ''
      },
      token: ''
    };
    if (sessionStorage.length > 0) {
      session.user.name = sessionStorage.key(0)!;
      session.token = sessionStorage.getItem(session.user.name)!;
    }
    return this.httpClient.post<Session>(ROUTE + '/api/session/logout', session);
  }
  checkLogin(): Observable<Session> {
    let session: Session = {
      user: {
        id: 0,
        name: '',
        passwd: ''
      },
      token: ''
    };
    if (sessionStorage.length > 0) {
      session.user.id = parseInt(sessionStorage.key(0)!);
      session.token = sessionStorage.getItem(session.user.name)!;
    }
    return this.httpClient.post<Session>(ROUTE + '/api/session/check', session);
  }

}
