import { Component } from '@angular/core';
import { Game } from './Game';
import { GameService } from '../../services/game/game.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NgFor, NgIf } from '@angular/common';
import { Score } from '../score/Score';

@Component({
  selector: 'app-game',
  standalone: true,
  imports: [NgIf, NgFor],
  templateUrl: './game.component.html',
  styleUrl: './game.component.css'
})
export class GameComponent {
  constructor(private gameService: GameService, private router: Router, private route: ActivatedRoute) { }
  game?: Game;
  scores?: Score[];
  id: number = Number(this.route.snapshot.paramMap.get('id'));
  ngOnInit() {
    this.getGame();
    this.getScores();
    console.log("game component running")
  }
  getGame(): void {
    this.gameService.getGame(this.id).subscribe(game => this.game = game)!;
    console.log("Game :" + this.game?.name + " retrieved");
  }
  getScores() {
    this.gameService.getScores(this.id).subscribe(scores => this.scores = scores);
    console.log("Scores for the game:" + this.game?.name + " retrieved");
  }
  getUsername(id: number) :string{
    let user : string = "";
    this.gameService.getUser(id).subscribe(user_=> user = user_.name);
    return user;
  }
}
