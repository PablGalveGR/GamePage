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
  private long pageViews;
  private long visits;
  private long downloads;

  public Game(long id, String name, String path, String description, String portrait, long pageViews, long visits,
      long downloads) {
    this.id = id;
    this.name = name;
    this.path = path;
    this.description = description;
    this.portrait = portrait;
    this.pageViews = pageViews;
    this.visits = visits;
    this.downloads = downloads;
  }

  public Game() {
  }

  public long getDownloads() {
    return this.downloads;
  }

  public void setDownloads(long downloads) {
    this.downloads = downloads;
  }

  public long getPageViews() {
    return this.pageViews;
  }

  public void setPageViews(long pageViews) {
    this.pageViews = pageViews;
  }

  public long getVisits() {
    return this.visits;
  }

  public void setVisits(long visits) {
    this.visits = visits;
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
