package gamepage.page.Users;

import java.util.List;
import java.util.Optional;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@CrossOrigin(origins = {"http://127.0.0.1:4200", "http://localhost:4200"})
@RestController
@RequestMapping("/api/users") // General path that invokes this controller

public class UserController {// Never return the password to the Client
  private final UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /*
   * private static final Logger log = LoggerFactory
   * .getLogger(Application.class);
   */
  // Select
  @GetMapping("")
  public List<User> findAllUsers() {
    List<User> users = userRepository.getAllUsers();
    System.out.println(users.toString());
    return users;
  }

  // General path plus an element to pass to the controller
  @GetMapping("{id}")
  User findOneUser(@PathVariable int id) {
    Optional<User> opUser = userRepository.getUserById(id);
    if (opUser.isEmpty()) {
      // throw new RunNotFoundException();
    }
    return opUser.get();
  }

  // Create
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("")
  void createRun(@Valid @RequestBody User user) {
    userRepository.createUser(user);
  }

  // Update
  @ResponseStatus(HttpStatus.ACCEPTED)
  @PutMapping("update/{id}")
  void updateRun(@Valid @RequestBody User user, @PathVariable int id) {
    // log.info(user.toString());
    userRepository.updateUser(user, id);
  }

  // Delete
  @ResponseStatus(HttpStatus.ACCEPTED)
  @DeleteMapping("delete/{id}")
  void deleteRun(@Valid @PathVariable int id) {
    userRepository.deleteUser(id);
  }
}
