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
    let users : Set<number> = new Set();
    for(let score of scores){//gets all the users whom played the game
      users.add(score.username);
    }
    let scoresSorted = scores.sort((score1, score2) =>score2.score - score1.score);//sort the array by score
    let scoreFiltered : Score[] = [];
    for(let user of users){
      scoreFiltered.push(scoresSorted.find(score => score.username == user)!);
    }
    scoreFiltered = scoreFiltered.sort((score1, score2) =>{///Sorts the array by the score
      if(score1.score > score2.score){
        return -1;
      }
      if( score1.score < score2.score){
        return 1;
      }
      return 0;
    });
    return of(scoreFiltered);

  }
  getScoresByUser(id:number) : Observable<Score[]>{
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
  }
}
