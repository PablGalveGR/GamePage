import { Component } from '@angular/core';
import { Game } from './Game';
import { GameService } from '../../services/game.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-game',
  standalone: true,
  imports: [NgIf],
  templateUrl: './game.component.html',
  styleUrl: './game.component.css'
})
export class GameComponent {
constructor(private gameService : GameService, private router : Router, private route: ActivatedRoute){}
game?: Game;
ngOnInit(){
  this.getGame();
  console.log("game component running")
}
getGame() : void{
  const id = Number(this.route.snapshot.paramMap.get('id'));
  this.gameService.getGame(id).subscribe(game => this.game = game)!;
  console.log(this.game);
}
}
