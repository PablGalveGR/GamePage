import { Component } from '@angular/core';
import { GameService } from '../services/game/game.service';
import { Game } from '../components/game/Game';
import { NgFor } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [NgFor],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {
  constructor(private gameService: GameService, private router: Router) {

  }
  games!: Game[];
  ngOnInit() {
    this.getGames();
  }
  getGames() {
    this.gameService.getGames().subscribe(games => this.games = games);
  }
  goToDetail(id: number) {
    this.router.navigate(['/game/' + id]);
  }

}
