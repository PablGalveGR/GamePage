import { Component, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Observable, of } from 'rxjs';
import { GameService } from '../../services/game/game.service';
import { Game } from '../game/Game';
import { Score } from '../score/Score';
import { User } from '../user/User';
import { Comment } from './Comment';
import { NgIf, NgFor, AsyncPipe } from '@angular/common';
import { CommentService } from '../../services/comment/comment.service';
import { UserService } from '../../services/user/user.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-comments',
  standalone: true,
  imports: [NgIf, NgFor, AsyncPipe, FormsModule],
  templateUrl: './comments.component.html',
  styleUrl: './comments.component.css'
})
export class CommentsComponent {
  constructor(
    private gameService: GameService,
    private router: Router,
    private route: ActivatedRoute,
    private commentService: CommentService,
    private userService: UserService) { }
  @Input() game?: Game;
  @Input() users?: Set<User>;
  comments: Observable<Comment[]> = new Observable;
  private gameId: number = Number(this.route.snapshot.paramMap.get('id'));
  commentsGUI: boolean = false;
  newComment: Comment = {
    id: 0,
    game: this.gameId,
    username: 0,
    comment: "",
    date: new Date()
  };
  showComments() {
    this.commentsGUI = !this.commentsGUI
    if (this.commentsGUI) {
      this.getComments();
    }
  }
  getComments(): void {
    this.gameService.getComments(this.gameId).subscribe(async (comments) => {
      let users: Set<User> = new Set<User>;
      for (let comment of comments) {//gets all the users whom commented on the game
        this.gameService.getUser(comment.username).subscribe(user => users.add(user));
        console.log(typeof comment.date);
      }
      this.users = users;
      console.log("Comments for the game:" + this.game?.name + " retrieved");
      comments = this.shortCommentsByDate(comments);
      this.comments = of(comments);
    });
  }
  shortCommentsByDate(comments: Comment[]): Comment[] {
    return comments.sort((c1, c2) => <any>new Date(c2.date) - <any>new Date(c1.date));
  }
  addComment() {
    if (this.checkIfSession()) {
      this.newComment.id = 0;
      this.newComment.game = this.gameId;
      this.newComment.date = new Date();
      this.userService.getUserbyName(sessionStorage.key(0)!).subscribe(id => {
        this.newComment.username = id;
        this.commentService.addComment(this.newComment).subscribe(a =>{
          //Reset comments
          this.newComment.comment = "";
          this.newComment.username = 0;
          this.getComments();
        });
      });
    }
  }
  getUser(): string {
    return sessionStorage.key(0)!;
  }
  getName(id: number): String {
    let name: String = "";
    try {
      if (this.users) {
        name = Array.from(this.users).find(user => user.id == id)!.name;
      }
    } catch (error) {
      name = "awA"
    }
    return name;
  }
  checkIfSession(): boolean {
    return sessionStorage.length > 0;
  }
}
