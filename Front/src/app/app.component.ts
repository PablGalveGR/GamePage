import { Component } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { LoginService } from './services/logIn/login.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  constructor(private router: Router, private loginService: LoginService) { }
  title = 'Front';
  private login : boolean = false;
  ngOnInit() {
    this.checkSession();
  }
  goTo(url: string) {
    this.router.navigate([url]);
  }
  checkSession(): boolean {
    this.login = false;
    if (sessionStorage.length > 0) {
      this.loginService.checkLogin().subscribe(session => {
        if (sessionStorage.key(0) == session.user.name) {
          sessionStorage.setItem(session.user.name, session.token);
          this.login = true;
        }
      });
    }
    return this.login;
  }
}

