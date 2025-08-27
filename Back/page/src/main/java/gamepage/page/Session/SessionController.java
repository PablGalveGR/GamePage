package gamepage.page.Session;

import java.util.ArrayList;
import java.util.List;
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
  // Logs a user creating a session, chekcs password and user
  Session doLogin(@Valid @RequestBody User user) {
    Session session = new Session();
    Optional<User> login = this.userRepository.getUserByName(user.getName());
    if (login.isPresent() && (!user.getPasswd().isBlank() ||
        !user.getPasswd().isEmpty())) {
      System.out.println("User " + user.getName() + " trying to login");
      if (login.get().getPasswd().equals(user.getPasswd())) {
        // If both passwords good:
        // Look for an already openned session for this user
        Integer[] sessionsFound = findSessionsForUser(user.getName());
        if (sessionsFound.length > 0) {
          session = updateSession(user, sessionsFound);
        } else {
          session = createSession(user);
        }

      } else {
        System.out.println("Password not correct");
      }
    } else {
      if ((user.getPasswd().isBlank() || user.getPasswd().isEmpty())) {
        System.out.println("User " + user.getName() +
            " no password sent from the client");
      } else {
        System.out.println("User " + user.getName() + " does not exist");
      }
    }
    return session;
  }

  // Creates and storages a session for a user
  Session createSession(User user) {
    Session session = new Session();
    if (!user.getName().isEmpty() || user.getName().length() > 0) {
      session.setUser(user);
      session.setToken(session.newToken());
      // Save Session
      SessionsInMemory.addSession(session);
      System.out.println("New session for user: " + user.getName() +
          " with token :" + session.getToken());
    }
    else {
      System.out.println("User is empty");
    }

    System.out.println("All sessions : " + SessionsInMemory.getSessionsInMemory().toString());
    return session;
  }

  // Updates the session for a user, all the sessions in storage are needed
  Session updateSession(User user, Integer[] sessions) {
    Session session = new Session();
    boolean removingSessions = false;
    Integer[] removed = removeSessions(sessions);
    removingSessions = removed.length > 0;
    if (removingSessions) {
      System.out.println("Updating session");
      session = createSession(user);
    }
    return session;
  }

  // Finds all sessions given a user and return an array with her indexes
  Integer[] findSessionsForUser(String username) {
    List<Integer> sessionsFound = new ArrayList<>();
    for (Session session_ : SessionsInMemory.sessions) {
      if (session_.user.getName().equals(username)) {
        sessionsFound.add(SessionsInMemory.sessions.indexOf(session_));
      }
    }
    Integer[] sessions = new Integer[sessionsFound.size()];
    sessions = sessionsFound.toArray(sessions);
    return sessions;
  }

  // Removes all sessions corresponding to the inputed indexes
  Integer[] removeSessions(Integer[] sessionsIndex) {
    if (sessionsIndex.length > 0) {
      for (int index : sessionsIndex) {
        SessionsInMemory.sessions.remove(index);
      }
    } else {
      System.out.println("No session found");
    }
    return sessionsIndex;
  }

  @RequestMapping("/check")
  // Cheks for the recieved session
  Session checkSession(@Valid @RequestBody Session session) {
    System.out.println("Checking session for " + session.getUser().getName());
    Session updatedSession = new Session();
    Integer[] sessionsFound = findSessionsForUser(session.getUser().getName());
    if (SessionsInMemory.sessions.size() > 0 && sessionsFound.length > 0) {
      boolean matchingSession = false;
      for (Integer index : sessionsFound) {// look for a matching username and token session in storage
        matchingSession = SessionsInMemory.sessions.get(index).getToken().equals(session.getToken())
            && SessionsInMemory.sessions.get(index).getUser().getName().equals(session.getUser().getName());
        if (matchingSession) {
          updatedSession = updateSession(session.getUser(), sessionsFound);
          System.out.println("Updating session for " + session.getUser().getId());
          break;
        }
      }
    } else {
      System.out.println("No session for " + session.getUser().toString());
    }
    return updatedSession;
  }

  @RequestMapping("/logout")
  // Deletes all sessions from a user
  Session logout(@Valid @RequestBody Session session) {
    if (SessionsInMemory.sessions.size() > 0) {
      removeSessions(findSessionsForUser(session.getUser().getName()));
      System.out.println("User " + session.getUser().getName() + " logged out");
    }
    return new Session();
  }

}
