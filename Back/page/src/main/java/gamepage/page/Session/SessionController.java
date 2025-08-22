package gamepage.page.Session;

import java.util.HashMap;
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
    Session session = new Session();
    Optional<User> login = this.userRepository.getUserByName(user.getName());

    if (login.isPresent() && (!user.getPasswd().isBlank() ||
        !user.getPasswd().isEmpty())) {
      System.out.println("User " + user.getName() + " trying to login");
      if (login.get().getPasswd().equals(user.getPasswd())) {
        // If both passwords good:
        session = createSession(user);
        System.out.println("New session for user: " + user.getName() +
            " with token :" + session.getToken());
      } else {
        session.setToken("Password incorrect");
      }
    } else {
      if ((user.getPasswd().isBlank() || user.getPasswd().isEmpty())) {
        session.setToken("Password is null");
        System.out.println("User " + user.getName() +
            " no password sent from the client");
      } else {
        System.out.println("User " + user.getName() + " does not exist");
        session.setToken("User " + user.getName() + " does not exist");
      }
    }
    return session;
  }

  Session createSession(User user) {
    Session session = new Session();
    session.setUser(user);
    session.setToken(session.newToken());
    // Save Session
    SessionsInMemory.addSession(session);
    System.out.println("Added session for " + user.getName());
    System.out.println("All sessions : " + SessionsInMemory.getSessionsInMemory().toString());
    return session;
  }

  HashMap<Integer, Boolean> findSession(Session session) {
    HashMap<Integer, Boolean> res = new HashMap<>();
    for (Session session_ : SessionsInMemory.sessions) {
      if (session_.user.getName().equals(session.getUser().getName())
          && session_.getToken().equals(session.getToken())) {
        System.out.println("Session found for " + session.getUser().getName());
        res.put(SessionsInMemory.sessions.indexOf(session_), true);
        System.out.println("Updating session for " + session.getUser().getName());

      }
    }
    return res;
  }

  Session removeSession(Session session) {
    HashMap<Integer, Boolean> sessionFound = findSession(session);
    if (sessionFound.size() > 0) {
      int index = sessionFound.keySet().iterator().next();
      SessionsInMemory.sessions.remove(index);
    } else {
    }
    return session;
  }

  @RequestMapping("/check")
  Session checkSession(@Valid @RequestBody Session session) {
    System.out.println("Checking session for " + session.getUser().getName());
    Session newSession = new Session();
    if (SessionsInMemory.sessions.size() > 0) {
      this.removeSession(session);
      System.out.println("Updating session for " + session.getUser().getId());
      newSession = createSession(session.user);
    } else {
      System.out.println("No session for " + session.getUser().toString());

    }
    return newSession;
  }

  @RequestMapping("/logout")
  Session logout(@Valid @RequestBody Session session) {
    if (SessionsInMemory.sessions.size() > 0) {
      this.removeSession(session);
      System.out.println("User " + session.getUser().getName() + " logged out");
    }
    return new Session();
  }

}
