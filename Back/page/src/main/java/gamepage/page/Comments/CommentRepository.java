package gamepage.page.Comments;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public class CommentRepository {
  private List<Comment> comments;
  private final JdbcClient jdbcClient;

  CommentRepository(JdbcClient jdbcClient) {
    this.jdbcClient = jdbcClient;
  }

  // Insert querys
  public int createComment(Comment comment) {
    String query = "INSERT INTO Comment " + "( game, username, comment, date)"
        + " VALUES( ?, ?, ?, ?);";
    var updated = jdbcClient.sql(query)
        .params(List.of(comment.getGame(),comment.getUsername(), comment.getComment(), comment.getDate())).update();
    Assert.state(updated == 1,
        "Failed to create Comments: " + comment.toString());
    return updated;
  }

  // Update querys
  public void updateComment(Comment comment, int id) {
    if (comment.getId() == id) {
      Optional<Comment> existingComment = getCommentById(id);
      if (existingComment.isPresent()) {
        String query = "UPDATE Comment SET game = ? , username = ?, comment = ?, date = ? WHERE id = ?;";
        var updated = jdbcClient.sql(query)
            .params(List.of(comment.getGame(),comment.getUsername(),comment.getComment(), comment.getDate() ))
            .update();
        Assert.state(updated == 1,
            "Failed to Update Comments: " + comment.toString());
      }
    }
  }

  // Delete querys
  public void deleteComment(int id) {
    Optional<Comment> existingComment = getCommentById(id);
    if (existingComment.isPresent()) {
      String query = "DELETE FROM Comment WHERE id = :id;";
      var updated = jdbcClient.sql(query).param("id", id).update();
      Assert.state(updated == 1,
          "Failed to Delete comment: " + existingComment.get().toString());
    }
  }

  // Select querys
  List<Comment> getAllComments() {
    String query = "SELECT * FROM Comment;";
    comments = jdbcClient.sql(query).query(Comment.class).list();
    return comments;
  }

  Optional<Comment> getCommentById(int id) {
    String query = "SELECT * FROM Comment WHERE id = :id;";
    Optional<Comment> comment = jdbcClient.sql(query).param("id", id)
        .query(Comment.class).optional();
    return comment;
  }
  List<Comment> getCommentsByGame(int game){
    String query = "SELECT * FROM comment WHERE game = :game";
    List<Comment> comments = jdbcClient.sql(query).param("game", game).query(Comment.class).list();
    return comments;
  }
}
