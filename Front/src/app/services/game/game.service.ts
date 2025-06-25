import { Injectable } from '@angular/core';
import { Game } from '../../components/game/Game';
import { GAMES } from '../../components/game/games';
import { Observable, of } from 'rxjs';
import { Score } from '../../components/score/Score';
import { ScoreService } from '../score/score.service';
import { User } from '../../components/user/User';
import { UserService } from '../user/user.service';

@Injectable({
  providedIn: 'root'
})
export class GameService {

  constructor(private scoreService : ScoreService, private userService : UserService) { }
  games : Game[] = GAMES;
  ngOnInit(){
    console.log("Service Game running");
  }
  getGame(id:number) : Observable<Game>{
    let game = this.games.find(i => i.id == id)!;
    return of(game);
  }
  getGames() : Observable<Game[]>{
    return of(this.games);
  }
  getScores(id:number) : Observable<Score[]>{
    return this.scoreService.getScoresByGame(id);
  }
  getUser(id:number) : Observable<User>{
    return this.userService.getUsername(id);
  }
}
