import { Injectable } from '@angular/core';
import { Score } from '../../components/score/Score';
//import { SCORES } from '../../components/score/scores';
import { Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ScoreService {

  constructor(private httpClient : HttpClient) { }
  //scores : Score[] = SCORES;
  getScores(): Observable<Score[]>{
    return this.httpClient.get<Score[]>('http://localhost:8080/api/scores');
  }
  getScoresByGame(id:number) : Observable<Score[]>{
    return this.httpClient.get<Score[]>('http://localhost:8080/api/scores/game/'+id);
    
  }
  /*getScoresByUser(id:number) : Observable<Score[]>{
    let scores : Score[] = this.scores.filter( score => score.username == id);
    let scoresSorted = scores.sort((score1, score2) =>{///Sorts the array by the score
      if(score1.score > score2.score){
        return 1;
      }
      if( score1.score < score2.score){
        return -1;
      }
      return 0;
    })
    return of(scores);
  }*/
}
