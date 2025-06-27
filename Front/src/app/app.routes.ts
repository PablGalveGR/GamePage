import { Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { GameComponent } from './components/game/game.component';
import { UserComponent } from './components/user/user.component';

export const routes: Routes = [
  {path: '', component:DashboardComponent},
  {path: 'game/:id', component:GameComponent},
  {path: 'users', component:UserComponent}
];
