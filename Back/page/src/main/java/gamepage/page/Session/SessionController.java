package gamepage.page.Session;

import java.util.Optional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import gamepage.page.Users.User;
import gamepage.page.Users.UserRepository;
import jakarta.validation.Valid;

@CrossOrigin(origins = { "http://127.0.0.1:4200", "http://localhost:4200" })
@RestController
@RequestMapping("/api/session") // General path that invokes this controller
public class SessionController {
  private final UserRepository userRepository;

  public SessionController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @RequestMapping("")
  Session doLogin(@Valid @RequestBody User user) {
    Optional<User> login = this.userRepository.getUserByName(user.getName());
    Session token = new Session();
    if (login.isPresent() && (!user.getPasswd().isBlank() || !user.getPasswd().isEmpty())) {
      System.out.println("User " + user.getName() + " trying to login");
      if (login.get().getPasswd().equals(user.getPasswd())) {
        /* If both passwords good: */
        token = createSession(user);
        System.out.println("New session for user: " + user.getName() + " with token :" + token.getToken());
      } else {
        token.setToken("Password incorrect");
      }
    } else {
      if ((user.getPasswd().isBlank() || user.getPasswd().isEmpty())) {
        token.setToken("Password is null");
        System.out.println("User " + user.getName() + " no password sent from the client");
      } else {
        System.out.println("User " + user.getName() + " does not exist");
        token.setToken("User " + user.getName() + " does not exist");
      }
    }
    return token;
  }

  Session createSession(User user) {
    Session session = new Session();
    session.setUser(user);
    session.setToken(session.newToken());
    // Save Session
    SessionsInMemory.addSession(session);
    System.out.println("All sessions : " + SessionsInMemory.getSessionsInMemory().toString());
    return session;
  }
  void logout(Session session){
    SessionsInMemory.deleteSession(session);
  }
}
