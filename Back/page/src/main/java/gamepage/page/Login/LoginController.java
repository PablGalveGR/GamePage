package gamepage.page.Login;

import java.util.Optional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gamepage.page.Session.Session;
import gamepage.page.Session.SessionsInMemory;
import gamepage.page.Users.User;
import gamepage.page.Users.UserRepository;
import jakarta.validation.Valid;

@CrossOrigin(origins = { "http://127.0.0.1:4200", "http://localhost:4200" })
@RestController
@RequestMapping("/api/login") // General path that invokes this controller
public class LoginController {
  private final UserRepository userRepository;

  public LoginController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
@RequestMapping("")
  String dologin(@Valid @RequestBody User user){
    Optional<User> login = this.userRepository.getUserByName( user.getName());
    String token = "";
    if(login.isPresent()){
      System.out.println("User " + user.getName() + " trying to login");
      token = "logged";
    }
    else{
      System.out.println("User " + user.getName() + " does not exist");
    }
    return token;
  }
  void createSession(){
    Session session = new Session();
  }
}
