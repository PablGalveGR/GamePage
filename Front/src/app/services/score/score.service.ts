import { Injectable } from '@angular/core';
import { Score } from '../../components/score/Score';
import { SCORES } from '../../components/score/scores';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ScoreService {

  constructor() { }
  scores : Score[] = SCORES;
  getScores(): Observable<Score[]>{
    return of(this.scores);
  }
  getScoresByGame(id:number) : Observable<Score[]>{
    let scores : Score[] = this.scores.filter( score => score.game == id);
    if(scores.length >0){

    }
    return of(scores);

  }
  /*getScoresByUser(id:number) : Observable<Score[]>{
    let scores : Score[] = this.scores.filter( score => score.user == id);
    if(scores.length >0){

    }
    return of(scores);
  }*/
}
