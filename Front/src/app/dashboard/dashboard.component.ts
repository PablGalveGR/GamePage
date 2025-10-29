import { Component, HostListener } from '@angular/core';
import { GameService } from '../services/game/game.service';
import { Game } from '../components/game/Game';
import { NgFor, NgIf } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [NgFor, NgIf],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {
  getScreenWidth: number = 0;
  desktop: boolean = true;
  constructor(private gameService: GameService, private router: Router) {

  }
  games: Game[] = new Array<Game>;
  AllGames: Game[] = new Array<Game>;
  ngOnInit() {
    this.getGames();
    this.getScreenWidth = window.innerWidth;
    this.resize();
  }
  @HostListener('window:resize', ['$event'])
    onWindowResize() { this.resize() }
  resize() {
    this.getScreenWidth = window.innerWidth;
    if (this.getScreenWidth <= 1020) {
      this.desktop = false;
    }
    else {
      this.desktop = true;
    }
  }
  getGames() {
    this.gameService.getGames().subscribe(games => { this.games = games; this.AllGames = games });
  }
  goToDetail(id: number) {
    this.router.navigate(['/game/' + id]);
  }
  filterBy(text: string) {
    this.games = Object.assign(this.AllGames);
    this.games = this.games.filter(i => i.name.toLowerCase().includes(text.toLowerCase()));
    console.log("Filtered :" + JSON.stringify(this.games));
    console.log("No Filtered :" + JSON.stringify(this.AllGames));
    if (this.games.length == 0) {
      console.log("No games found");
    }
  }
  clearFilter(filter?: HTMLInputElement) {
    if (filter) {
      filter.value = "";
    }
    this.filterBy("");
  }


}
