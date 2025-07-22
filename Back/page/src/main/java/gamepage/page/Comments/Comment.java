package gamepage.page.Comments;
import java.time.LocalDateTime;

public class Comment {
  int id;
  int game;
  int username;
  String comment;
  LocalDateTime date;

  public Comment() {
  }

  public Comment(int id, int game, int username, String text, LocalDateTime date) {
    this.id = id;
    this.game = game;
    this.username = username;
    this.comment = text;
    this.date = date;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getGame() {
    return this.game;
  }

  public void setGame(int game) {
    this.game = game;
  }

  public int getUsername() {
    return this.username;
  }

  public void setUsername(int username) {
    this.username = username;
  }

  public String getComment() {
    return this.comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public LocalDateTime getDate() {
    return this.date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", game='" + getGame() + "'" +
      ", username='" + getUsername() + "'" +
      ", text='" + getComment() + "'" +
      ", date='" + getDate() + "'" +
      "}";
  }
}
