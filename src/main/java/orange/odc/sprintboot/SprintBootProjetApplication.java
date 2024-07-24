package orange.odc.sprintboot;

import orange.odc.sprintboot.models.User;
import orange.odc.sprintboot.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SprintBootProjetApplication {

	public static void main(String[] args) {
		SpringApplication.run(SprintBootProjetApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(UserService service) {
		return args ->{

			User user = new User();
			user.setLastName("Mamy");
			user.setFirtName("Ousmane");

			user.setEmail("ousmane@gmail.com");
			user.setPassword("12345");
			user.setPasswordConf("12345");

			service.saveUser(user);

		};
	}

}
