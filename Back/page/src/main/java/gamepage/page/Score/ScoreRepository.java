package gamepage.page.Score;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public class ScoreRepository {
  private List<Score> scores;
  private final JdbcClient jdbcClient;

  ScoreRepository(JdbcClient jdbcClient) {
    this.jdbcClient = jdbcClient;
  }

  // Insert querys
  public int createScore(Score score) {
    String query = "INSERT INTO Score" + "( game, username,  score)"
        + " VALUES(?, ?, ?);";
    var updated = jdbcClient.sql(query)
        .params(List.of(score.getGame(), score.getUsername(), score.getScore())).update();
    Assert.state(updated == 1,
        "Failed to create Score: " + score.getId());
    return updated;
  }

  // Update querys
  public void updateScore(Score score, int id) {
    if (score.getId() == id) {
      Optional<Score> existingGame = getScoreById(id);
      if (existingGame.isPresent()) {
        String query = "UPDATE Score SET game = ?, username = ?, score = ? WHERE id = ?;";
        var updated = jdbcClient.sql(query)
            .params(List.of(score.getGame(), score.getUsername(),score.getScore(), score.getId()))
            .update();
        Assert.state(updated == 1,
            "Failed to Update Score: " + score.getId());
      }
    }
  }

  // Delete querys
  public void deleteScore(int id) {
    Optional<Score> existingScore = getScoreById(id);
    if (existingScore.isPresent()) {
      String query = "DELETE FROM Score WHERE id = :id;";
      var updated = jdbcClient.sql(query).param("id", id).update();
      Assert.state(updated == 1,
          "Failed to Delete Score: " + existingScore.get().getId());
    }
  }

  // Select querys
  List<Score> getAllScores() {
    String query = "SELECT * FROM Score;";
    scores = jdbcClient.sql(query).query(Score.class).list();
    return scores;
  }

  List<Score> getAllScoresByUser( int user) {
    String query = "SELECT * from Score WHERE username = :user;";
    List<Score> scores = jdbcClient.sql(query).param("user", user).query(Score.class).list();
    return scores;
  }

  List<Score> getAllScoresByGame(int game) {
    String query = "SELECT * from Score WHERE game = :game;";
    List<Score> scores = jdbcClient.sql(query).param("game", game).query(Score.class).list();
    return scores;
  }

  Optional<Score> getScoreById(int id) {
    String query = "SELECT * FROM Score WHERE id = :id;";
    Optional<Score> score = jdbcClient.sql(query).param("id", id)
        .query(Score.class).optional();
    return score;
  }
}
