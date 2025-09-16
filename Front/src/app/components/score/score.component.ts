import { Component, Input, Output } from '@angular/core';
import { Score } from './Score';
import { GameService } from '../../services/game/game.service';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../user/User';
import { Game } from '../game/Game';
import { Observable, of } from 'rxjs';
import { NgIf, NgFor, AsyncPipe } from '@angular/common';

@Component({
  selector: 'app-score',
  standalone: true,
  imports: [NgIf, NgFor, AsyncPipe],
  templateUrl: './score.component.html',
  styleUrl: './score.component.css'
})
export class ScoreComponent {
  constructor(private gameService: GameService, private router: Router, private route: ActivatedRoute) { }
  @Input() game?: Game;
  users!: Set<User>;
  scores: Observable<Score[]> = new Observable;
  private gameId: number = Number(this.route.snapshot.paramMap.get('id'));
  ngOnInit() {
    this.getScores();
    console.log("game component running")
  }
  getName(id: number): String {
    let name: String = "";
    try {
      if (this.users) {
        name = Array.from(this.users).find(user => user.id == id)!.name;
      }
    } catch (error) {
      name = "awA"
    }
    return name;
  }
  getScores(): void {
    this.gameService.getScores(this.gameId).subscribe(async (scores) => {
      let users: Set<number> = new Set();//user ids
      for (let score of scores) {//gets all the users whom played the game
        this.gameService.getUser(score.username).subscribe(user => this.users.add(user));
        users.add(score.username);//User id
      }
      let scoresSorted = scores.sort((score1, score2) => score2.score - score1.score);//sort the array by score
      let scoreFiltered: Score[] = [];

      for (let user of users) {//Take only the highest score from each user
        scoreFiltered.push(scoresSorted.find(score => score.username == user)!);
      }
      scoreFiltered = scoreFiltered.sort((score1, score2) => score2.score - score1.score);///Sorts the array by the score
      console.log("Scores for the game:" + this.game?.name + " retrieved");
      this.scores = of(scoreFiltered);
    });
  }
}
