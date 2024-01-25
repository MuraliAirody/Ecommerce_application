package com.ecommerce;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ECommerceApplication {

	public static void main(String[] args) {
		// Load values from .env file
		Dotenv dotenv = Dotenv.configure().load();

		// Get values
		String apiKey = dotenv.get("API_KEY");
		String secretKey = dotenv.get("SECRET_KEY");

		// You can use these values as needed
		System.setProperty("spring.api.key", apiKey);
		System.setProperty("spring.secret.key", secretKey);

		SpringApplication.run(ECommerceApplication.class, args);
	}

}
