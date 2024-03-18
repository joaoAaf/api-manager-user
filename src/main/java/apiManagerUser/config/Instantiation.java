package apiManagerUser.config;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import apiManagerUser.domain.User;
import apiManagerUser.repository.UserRepository;
import apiManagerUser.services.UserService;

@Configuration
public class Instantiation implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private UserService userServ;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat fDate = new SimpleDateFormat("dd/MM/yyyy");
		fDate.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		if(userRepo.count() == 0) {
			User user1 = new User("Fulano de Tal", "fulano@gmail.com", "fulano","123456");
			userServ.insert(user1);
			User user2 = new User("Sicrano da Silva", "sicrano@gmail.com", "sicrano","123456");
			userServ.insert(user2);
			User user3 = new User("Beltrano Costa", "beltrano@gmail.com", "beltrano","123456");
			userServ.insert(user3);
		}
		
	}

}
