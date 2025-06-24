package gamepage.page;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import gamepage.page.Users.UserRepository;

@SpringBootApplication
public class PageApplication {

  public static void main(String[] args) {
    SpringApplication.run(PageApplication.class, args);
    System.out.println("Welcome to GamePage");
    //System.out.println("Users: " + user.toString());

  }
  @Bean
	CommandLineRunner GamePage(UserRepository userRepository) {
		return args -> {

		};
	}

}
