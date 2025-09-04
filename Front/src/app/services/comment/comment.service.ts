import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Comment } from '../../components/comments/Comment';
import { ROUTE } from '../ROUTE';

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
    return this.httpClient.get<Comment[]>(ROUTE + '/api/comments/game/' + game);
  }
  addComment(comment : Comment) : Observable<any>{
    return this.httpClient.post<Comment>(ROUTE + '/api/comments', comment);
  }
  
}
