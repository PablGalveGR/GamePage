package gamepage.page.Session;

import java.util.ArrayList;
import java.util.List;

public class SessionsInMemory {
  static List<Session> sessions = new ArrayList<>();

  public SessionsInMemory() {
  }
  public static int countSessions(){
    return SessionsInMemory.sessions.size();
  }
  public static List<Session> getSessionsInMemory (){
    return SessionsInMemory.sessions;
  }
  public static void  addSession(Session session){
    SessionsInMemory.sessions.add(session);
  }
  public static void deleteSession(Session session){
    SessionsInMemory.sessions.remove(session);
  }
  @Override
  public String toString() {
    return "{" + sessions.toString() +
        "}";
  }
}
