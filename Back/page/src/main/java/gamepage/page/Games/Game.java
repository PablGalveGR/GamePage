package gamepage.page.Games;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Game")
public class Game {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String name;
  private String path;
  private String description;
  private String portrait;

  public Game(long id, String name, String path, String description, String portrait) {
    this.id = id;
    this.name = name;
    this.path = path;
    this.description = description;
    this.portrait = portrait;
  }

  public Game() {
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

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
