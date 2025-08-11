import { Component } from '@angular/core';
import { GameService } from '../services/game/game.service';
import { Game } from '../components/game/Game';
import { NgFor } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [NgFor],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {
  constructor(private gameService: GameService, private router: Router) {

  }
  games!: Game[];
  AllGames!: Game[];
  ngOnInit() {
    this.getGames();
  }
  getGames() {
    this.gameService.getGames().subscribe(games => {this.games = games; this.AllGames = games });
  }
  getGamesByName(name : string){
    this.games = this.gameService.getGamesByName(name, this.games);
  }
  goToDetail(id: number) {
    this.router.navigate(['/game/' + id]);
  }
  filterBy(text: string) {
    this.games = Object.assign(this.AllGames);
    this.games = this.games.filter(i => i.name.toLowerCase().includes(text.toLowerCase()));
    console.log("Filtered :" + JSON.stringify(this.games));
    console.log("No Filtered :" + JSON.stringify(this.AllGames));
    if(this.games.length == 0){
      console.log("No games found");
    }
  }
  clearFilter(filter? : HTMLInputElement) {
    if(filter){
      filter.value = "";
    }
    this.filterBy("");
  }

}
