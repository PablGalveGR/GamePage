import { Injectable } from '@angular/core';
import { Game } from '../components/game/Game';
import { GAMES } from '../components/game/games';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GameService {

  constructor() { }
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
}
