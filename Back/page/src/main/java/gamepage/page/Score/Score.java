package gamepage.page.Score;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Game")
public class Score {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private long game;
  private long username;
  private long score;

  public Score() {
  }

  public Score(long id, long game, long username, long score) {
    this.id = id;
    this.game = game;
    this.username = username;
    this.score = score;
  }

  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getGame() {
    return this.game;
  }

  public void setGame(long game) {
    this.game = game;
  }

  public long getUsername() {
    return this.username;
  }

  public void setUsername(long username) {
    this.username = username;
  }

  public long getScore() {
    return this.score;
  }

  public void setScore(long score) {
    this.score = score;
  }


  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", game='" + getGame() + "'" +
      ", username='" + getUsername() + "'" +
      ", score='" + getScore() + "'" +
      "}";
  }


}
