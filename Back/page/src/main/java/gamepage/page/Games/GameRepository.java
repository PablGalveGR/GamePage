package gamepage.page.Games;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public class GameRepository {
  private List<Game> games;
  private final JdbcClient jdbcClient;

  GameRepository(JdbcClient jdbcClient) {
    this.jdbcClient = jdbcClient;
  }

  // Insert querys
  public int createGame(Game game) {
    String query = "INSERT INTO Game " + "( name, path, description, portrait)"
        + " VALUES( ?, ?, ?, ?);";
    var updated = jdbcClient.sql(query)
        .params(List.of(game.getName(),game.getPath(), game.getPortrait())).update();
    Assert.state(updated == 1,
        "Failed to create Games: " + game.getName());
    return updated;
  }

  // Update querys
  public void updateGame(Game game, int id) {
    if (game.getId() == id) {
      Optional<Game> existingGame = getGameById(id);
      if (existingGame.isPresent()) {
        String query = "UPDATE Game SET name = ? , path = ?, description = ?, portrait = ? WHERE id = ?;";
        var updated = jdbcClient.sql(query)
            .params(List.of(game.getName(),game.getPath(),game.getPortrait(), game.getId() ))
            .update();
        Assert.state(updated == 1,
            "Failed to Update Games: " + game.getName());
      }
    }
  }

  // Delete querys
  public void deleteGame(int id) {
    Optional<Game> existingGame = getGameById(id);
    if (existingGame.isPresent()) {
      String queryDependencies = "DELETE FROM SCORE WHERE game = :id;";
      var deleteDependencies = jdbcClient.sql(queryDependencies).param("id", id).update();
      Assert.state(deleteDependencies == 1,
          "Failed to Delete Run: " + existingGame.get().getName());
      String query = "DELETE FROM Game WHERE id = :id;";
      var updated = jdbcClient.sql(query).param("id", id).update();
      Assert.state(updated == 1,
          "Failed to Delete Run: " + existingGame.get().getName());
    }
  }

  // Select querys
  List<Game> getAllGames() {
    String query = "SELECT * FROM Game;";
    games = jdbcClient.sql(query).query(Game.class).list();
    return games;
  }

  Optional<Game> getGameById(int id) {
    String query = "SELECT * FROM Game WHERE id = :id;";
    Optional<Game> game = jdbcClient.sql(query).param("id", id)
        .query(Game.class).optional();
    return game;
  }
  String getGameName(int id){
    String query = "";
    Game game = (Game) jdbcClient.sql(query).param("id", id).query(Game.class);
    return game.getName();
  }
}
