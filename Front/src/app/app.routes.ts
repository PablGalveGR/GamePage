import { Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { GameComponent } from './components/game/game.component';
import { UserComponent } from './components/user/user.component';
import { SignUpComponent } from './components/sign-up/sign-up.component';
import { LoginComponent } from './components/login/login.component';


export const routes: Routes = [
  {path: '', component:DashboardComponent},
  {path: 'game/:id', component:GameComponent},
  {path: 'users', component:UserComponent},
  {path: 'signUp', component:SignUpComponent},
  {path: 'login', component:LoginComponent}
];
