import { Injectable } from '@angular/core';
import { Game } from '../../components/game/Game';
import { Observable, of } from 'rxjs';
import { User } from '../../components/user/User';
import { UserService } from '../user/user.service';
import { HttpClient } from '@angular/common/http';
import { Comment } from '../../components/comment/Comment';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(
    private httpClient: HttpClient) { }
  ngOnInit() {
    console.log("Service Comments running");
  }
  getCommentsByGame(game :number): Observable<Comment[]>{
    return this.httpClient.get<Comment[]>('http://localhost:8080/api/comments/game/' + game);
  }
  
}
