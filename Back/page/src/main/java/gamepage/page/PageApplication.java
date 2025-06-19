package gamepage.page;

import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.codec.AbstractDataBufferDecoder;

import ch.qos.logback.core.net.SyslogOutputStream;
import gamepage.page.Users.User;
import gamepage.page.Users.UserRepository;

@SpringBootApplication
public class PageApplication {

    public static void main(String[] args) {
		SpringApplication.run(PageApplication.class, args);
        User user = new User(0, "afdsw gw", "ASASDW@gmail.com");
        System.out.println("Welcome to GamePage");
        System.out.println("Users: " + user.toString() );
		
	}
    

}
