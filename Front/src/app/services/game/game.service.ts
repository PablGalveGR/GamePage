import { Injectable } from '@angular/core';
import { Game } from '../../components/game/Game';
import { Observable, of } from 'rxjs';
import { Score } from '../../components/score/Score';
import { ScoreService } from '../score/score.service';
import { User } from '../../components/user/User';
import { UserService } from '../user/user.service';
import { HttpClient } from '@angular/common/http';
import { CommentService } from '../comment/comment.service';
import { Comment } from '../../components/comment/Comment';
import { ROUTE } from '../ROUTE';

@Injectable({
  providedIn: 'root'
})
export class GameService {

  constructor(
    private commentService: CommentService,
    private scoreService: ScoreService,
    private userService: UserService,
    private httpClient: HttpClient) { }
  ngOnInit() {
    console.log("Service Game running");
  }
  getGame(id: number): Observable<Game> {
    return this.httpClient.get<Game>(ROUTE +'/api/games/'+id);
  }
  getGames(): Observable<Game[]> {
    return this.httpClient.get<Game[]>(ROUTE + '/api/games');
  }
  getScores(id: number): Observable<Score[]> {
    return this.scoreService.getScoresByGame(id);
  }
  getUser(id: number): Observable<User> {
    return this.userService.getUser(id);
  }
  getUserName(id : number) : Observable<User>{
    return this.userService.getName(id);
  }
  getComments(id: number): Observable<Comment[]>{
    return this.commentService.getCommentsByGame(id);
  }
}
