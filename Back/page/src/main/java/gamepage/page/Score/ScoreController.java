package gamepage.page.Score;

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
@RequestMapping("/api/scores") // General path that invokes this controller
@CrossOrigin
public class ScoreController {// Never return the password to the Client
  private final ScoreRepository scoreRepository;

  public ScoreController(ScoreRepository scoreRepository) {
    this.scoreRepository = scoreRepository;
  }

  /*
   * private static final Logger log = LoggerFactory
   * .getLogger(Application.class);
   */
  // Select
  @GetMapping("")
  public List<Score> findAllScores() {
    List<Score> games = scoreRepository.getAllScores();
    System.out.println(games.toString());
    return games;
  }

  // General path plus an element to pass to the controller
  @GetMapping("{id}")
  Score findOneScore(@PathVariable int id) {
    Optional<Score> opScore = scoreRepository.getScoreById(id);
    if (opScore.isEmpty()) {
      // throw new RunNotFoundException();
    }
    return opScore.get();
  }

  @GetMapping("game/{id}")
  public List<Score> getScoreByGame(@PathVariable int id) {
    List<Score> scores = scoreRepository.getAllScoresByGame(id);

    return scores;
  }

  @GetMapping("username/{id}")
  public List<Score> getScoreByUser(@PathVariable int id) {

    List<Score> scores = scoreRepository.getAllScoresByUser(id);

    return scores;
  }

  // Create
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("")
  void createRun(@Valid @RequestBody Score game) {
    scoreRepository.createScore(game);
  }

  // Update
  @ResponseStatus(HttpStatus.ACCEPTED)
  @PutMapping("update/{id}")
  void updateRun(@Valid @RequestBody Score game, @PathVariable int id) {
    // log.info(user.toString());
    scoreRepository.updateScore(game, id);
  }

  // Delete
  @ResponseStatus(HttpStatus.ACCEPTED)
  @DeleteMapping("delete/{id}")
  void deleteRun(@Valid @PathVariable int id) {
    scoreRepository.deleteScore(id);
  }
}
