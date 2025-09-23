import { Component } from '@angular/core';
import { Game } from './Game';
import { GameService } from '../../services/game/game.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NgFor, NgIf } from '@angular/common';
import { Score } from '../score/Score';
import { User } from '../user/User';
import { AsyncPipe } from '@angular/common';
import { Observable, of } from 'rxjs';
import { Comment } from '../comments/Comment';
import { CommentsComponent } from '../comments/comments.component';
import { ScoreComponent } from '../score/score.component';

@Component({
  selector: 'app-game',
  standalone: true,
  imports: [NgIf, NgFor, AsyncPipe, CommentsComponent, ScoreComponent],
  templateUrl: './game.component.html',
  styleUrl: './game.component.css'
})
export class GameComponent {
  constructor(private gameService: GameService, private router: Router, private route: ActivatedRoute) { }
  game?: Game;
  scores: Observable<Score[]> = new Observable;
  comments: Observable<Comment[]> = new Observable;
  users: Set<User> = new Set();
  private id: number = Number(this.route.snapshot.paramMap.get('id'));
  commentsGUI: boolean = false;
  ngOnInit() {
    this.getGame();
    //this.getScores();
    console.log("game component running");
  }
  getGame(): void {
    this.gameService.getGame(this.id).subscribe(game => {
      this.game = game;
      this.addPageVisits(this.game)
    })!;
    console.log("Game :" + this.game?.name + " retrieved");
  }
  addPageVisits(game: Game) {
    game.pageViews += 1;
    this.gameService.updateVisits(game);
    console.log("New visit for the game");
  }
}
