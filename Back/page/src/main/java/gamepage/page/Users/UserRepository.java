package gamepage.page.Users;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository

public class UserRepository {
  private List<User> users;
  private final JdbcClient jdbcClient;

  UserRepository(JdbcClient jdbcClient) {
    this.jdbcClient = jdbcClient;
  }

  // Insert querys
  public int createUser(User user) {
    String query = "INSERT INTO USERS" + "( name, passwd)"
        + " VALUES(?, ?);";
    var updated = jdbcClient.sql(query)
        .params(List.of(user.getName(),user.getPaswd())).update();
    Assert.state(updated == 1,
        "Failed to create User: " + user.getName());
    return updated;
  }

  // Update querys
  public void updateUser(User user, int id) {
    if (user.getId() == id) {
      Optional<User> existingUser = getUserById(id);
      if (existingUser.isPresent()) {
        String query = "UPDATE USERS SET name = ?, passwd = ? WHERE id = ?;";
        var updated = jdbcClient.sql(query)
            .params(List.of(user.getName(),user.getPaswd(), user.getId()))
            .update();
        Assert.state(updated == 1,
            "Failed to Update User: " + user.getName());
      }
    }
  }

  // Delete querys
  public void deleteUser(int id) {
    Optional<User> existingUser = getUserById(id);
    if (existingUser.isPresent()) {
      String queryDependencies = "DELETE FROM SCORE WHERE username = :id;";
      var deleteDependencies = jdbcClient.sql(queryDependencies).param("id", id).update();
      Assert.state(deleteDependencies == 1,
          "Failed to Delete Run: " + existingUser.get().getName());
      String query = "DELETE FROM USERS WHERE id = :id;";
      var updated = jdbcClient.sql(query).param("id", id).update();
      Assert.state(updated == 1,
          "Failed to Delete Run: " + existingUser.get().getName());
    }
  }

  // Select querys
  
  List<User> getAllUsers() {
    String query = "SELECT * FROM USERS;";
    users = jdbcClient.sql(query).query(User.class).list();
    return users;
  }

  Optional<User> getUserById(int id) {
    String query = "SELECT * FROM USERS WHERE id = :id;";
    Optional<User> user = jdbcClient.sql(query).param("id", id)
        .query(User.class).optional();
    return user;
  }

  Optional<User> getUserNameById(int id) {
    String query = "SELECT * FROM USERS r WHERE id = :id;";
    Optional<User> user = jdbcClient.sql(query).param("id", id)
        .query(User.class).optional();
    if (user.isPresent()) {
      return user;
    }
    return null;
  }
}
