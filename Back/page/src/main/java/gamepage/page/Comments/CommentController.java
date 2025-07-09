package gamepage.page.Comments;
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
@RequestMapping("/api/comments") // General path that invokes this controller
@CrossOrigin
public class CommentController {//Never return the password to the Client
  private final CommentRepository commentRepository;
 public CommentController (CommentRepository commentReposijtory){
  this.commentRepository = commentReposijtory;
 }
 /*private static final Logger log = LoggerFactory
      .getLogger(Application.class);
*/
 //Select
@GetMapping("")
  public List<Comment> findAllComments() {
    List<Comment> comments = commentRepository.getAllComments();
    System.out.println(comments.toString());
    return comments;
  }
// General path plus an element to pass to the controller
  @GetMapping("{id}") 
  Comment findOneComment(@PathVariable int id) {
    Optional<Comment> opComment = commentRepository.getCommentById(id);
    if (opComment.isEmpty()) {
      //throw new RunNotFoundException();
    }
    return opComment.get();
  }
  @GetMapping("game/{id}") 
  List<Comment> findCommentByGame(@PathVariable int id) {
    List<Comment> opComment = commentRepository.getCommentsByGame(id);
    if (opComment.isEmpty()) {
      //throw new RunNotFoundException();
    }
    return opComment;
  }
 // Create
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("")
  void createRun(@Valid @RequestBody Comment comment) {
    commentRepository.createComment(comment);
  }

  // Update
  @ResponseStatus(HttpStatus.ACCEPTED)
  @PutMapping("update/{id}")
  void updateRun(@Valid @RequestBody Comment comment, @PathVariable int id) {
    //log.info(user.toString());
    commentRepository.updateComment(comment, id);
  }

  // Delete
  @ResponseStatus(HttpStatus.ACCEPTED)
  @DeleteMapping("delete/{id}")
  void deleteRun(@Valid @PathVariable int id) {
    commentRepository.deleteComment(id);
  }
}
