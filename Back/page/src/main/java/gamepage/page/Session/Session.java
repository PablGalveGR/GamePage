package gamepage.page.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gamepage.page.Users.User;

public class Session {
  User user;
  String token;

  public Session() {
  }

  public Session(User user) {
    this.setUser(user);
  }

  public Session(User user, String session) {
    this.setUser(user);
    this.token = session;
  }

  public User getUser() {
    return this.user;
  }

  public void setUser(User user) {
    user.setPasswd("");
    this.user = user;
  }

  public String getToken() {
    return this.token;
  }

  public void setToken(String session) {
    this.token = session;
  }

  @Override
  public String toString() {
    return "{" +
        " user='" + getUser() + "'" +
        ", token='" + getToken() + "'" +
        "}";
  }

  public String newToken() {
    String token = this.generateToken();
    List<String> allTokens = new ArrayList<String>();
    if (SessionsInMemory.sessions.size() != 0) {
      for (Session session : SessionsInMemory.sessions) {
        allTokens.add(session.getToken());
      }
      if (allTokens.contains(token)) {
        token = this.generateToken();
      }
    }

    return token;
  }

  private String generateToken() {
    int leftLimit = 48; // numeral '0'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 40;
    Random random = new Random();
    String token = random.ints(leftLimit, rightLimit + 1)
        .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
        .limit(targetStringLength)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();
    return token;
  }
}
