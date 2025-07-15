package gamepage.page.Login;

import gamepage.page.Users.User;
import java.util.Objects;

public class Login {
  User user;
  String token;

  public Login() {
  }

  public Login(User user, String token) {
    this.user = user;
    this.token = token;
  }

  public User getUser() {
    return this.user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getToken() {
    return this.token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public Login user(User user) {
    setUser(user);
    return this;
  }

  public Login token(String token) {
    setToken(token);
    return this;
  }

  @Override
  public String toString() {
    return "{" +
      " user='" + getUser() + "'" +
      ", token='" + getToken() + "'" +
      "}";
  }
}
