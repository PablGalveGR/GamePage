package gamepage.page.Games;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "Game")
public class Game {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String name;
  private String path;
  private String description;

  public Game(long id, String name, String path, String description, String portrait) {
    this.id = id;
    this.name = name;
    this.path = path;
    this.description = description;
    this.portrait = portrait;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Game id(long id) {
    setId(id);
    return this;
  }

  public Game name(String name) {
    setName(name);
    return this;
  }

  public Game path(String path) {
    setPath(path);
    return this;
  }

  public Game description(String description) {
    setDescription(description);
    return this;
  }

  public Game portrait(String portrait) {
    setPortrait(portrait);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Game)) {
            return false;
        }
        Game game = (Game) o;
        return id == game.id && Objects.equals(name, game.name) && Objects.equals(path, game.path) && Objects.equals(description, game.description) && Objects.equals(portrait, game.portrait);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, path, description, portrait);
  }
  private String portrait;

  public Game() {
  }

  public Game(long id, String name, String path, String portrait) {
    this.id = id;
    this.name = name;
    this.path = path;
    this.portrait = portrait;
  }

  // standard constructors / setters / getters / toString
  public void setName(String name) {
    this.name = name;
  }

  public String getPath() {
    return this.path;
  }

  public String getPortrait() {
    return this.portrait;
  }

  public void setPortrait(String portrait) {
    this.portrait = portrait;
  }

  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setPath(String path) {
    this.path = path;
  }


  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", name='" + getName() + "'" +
      ", path='" + getPath() + "'" +
      ", image='" + getPortrait() + "'" +
      "}";
  }
 

}
