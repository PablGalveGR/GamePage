package gamepage.page.Games;
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
@RestController
@RequestMapping("/api/games") // General path that invokes this controller
@CrossOrigin
public class GameController {//Never return the password to the Client
  private final GameRepository gameRepository;
 public GameController (GameRepository gameRepository){
  this.gameRepository = gameRepository;
 }
 /*private static final Logger log = LoggerFactory
      .getLogger(Application.class);
*/
 //Select
@GetMapping("")
  public List<Game> findAllGames() {
    List<Game> games = gameRepository.getAllGames();
    System.out.println(games.toString());
    return games;
  }
// General path plus an element to pass to the controller
  @GetMapping("{id}") 
  Game findOneGame(@PathVariable int id) {
    Optional<Game> opGame = gameRepository.getGameById(id);
    if (opGame.isEmpty()) {
      //throw new RunNotFoundException();
    }
    return opGame.get();
  }
 // Create
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("")
  void createRun(@Valid @RequestBody Game game) {
    gameRepository.createGame(game);
  }

  // Update
  @ResponseStatus(HttpStatus.ACCEPTED)
  @PutMapping("update/{id}")
  void updateRun(@Valid @RequestBody Game game, @PathVariable int id) {
    //log.info(user.toString());
    gameRepository.updateGame(game, id);
  }

  // Delete
  @ResponseStatus(HttpStatus.ACCEPTED)
  @DeleteMapping("delete/{id}")
  void deleteRun(@Valid @PathVariable int id) {
    gameRepository.deleteGame(id);
  }
}
