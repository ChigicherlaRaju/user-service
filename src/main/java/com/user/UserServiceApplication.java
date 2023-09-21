package com.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.user.model.User;
import com.user.repository.UserRepository;

@SpringBootApplication
public class UserServiceApplication {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository) {
		return (args) -> {
			userRepository.save(new User("Ramesh", "Kumar", "ramesh.kumar@gmail.com", "1"));
		};
	}

}