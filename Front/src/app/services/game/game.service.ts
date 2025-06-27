import { Injectable } from '@angular/core';
import { Game } from '../../components/game/Game';
import { GAMES } from '../../components/game/games';
import { Observable, of } from 'rxjs';
import { Score } from '../../components/score/Score';
import { ScoreService } from '../score/score.service';
import { User } from '../../components/user/User';
import { UserService } from '../user/user.service';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class GameService {

  constructor(
    private scoreService: ScoreService,
    private userService: UserService,
    private httpClient: HttpClient) { }
  games: Game[] = GAMES;
  ngOnInit() {
    console.log("Service Game running");
  }
  getGame(id: number): Observable<Game> {
    return this.httpClient.get<Game>('http://localhost:8080/api/games/'+id);
  }
  getGames(): Observable<Game[]> {
    return this.httpClient.get<Game[]>('http://localhost:8080' + '/api/games');
  }
  getScores(id: number): Observable<Score[]> {
    return this.scoreService.getScoresByGame(id);
  }
  getUser(id: number): Observable<User> {
    return this.userService.getUsername(id);
  }
}
