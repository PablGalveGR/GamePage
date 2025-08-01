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
        // System.out.println("Updating session for " + session.getUser().getName());

      }
    }
    return res;
  }

  Session removeSession(Session session) {
    HashMap<Integer, Boolean> sessionFound = findSession(session);
    if (sessionFound.size() > 0) {
      int index = sessionFound.keySet().iterator().next();
      SessionsInMemory.sessions.remove(index);
    }
    else{

    }
    return session;
  }

  @RequestMapping("/check")
  Session checkSession(@Valid @RequestBody Session session) {
    System.out.println("Checking session for " + session.getUser().getName());
    Session newSession = new Session();
    if (SessionsInMemory.sessions.size() > 0 && !this.findSession(session).isEmpty()) {
      this.removeSession(session);
      System.out.println("Updating session for " + session.getUser().getName());
      newSession = createSession(session.user);
    }
    else{
      newSession.user.setName("null");
      newSession.setToken(null);
    }
    return newSession;
  }

  @RequestMapping("/logout")
  Session logout(@Valid @RequestBody Session session) {
    if(SessionsInMemory.sessions.size() > 0){
      this.removeSession(session);
      System.out.println("User "+ session.getUser().getName() + " logged out");
    }
    return new Session();
  }

}
