package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import io.github.cdimascio.dotenv.Dotenv;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories("com.example.demo.repository")
@EnableMongoRepositories("com.example.demo.repository")
public class TrashCalendarApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
		System.setProperty("MONGODB_URI", dotenv.get("MONGODB_URI"));
		SpringApplication.run(TrashCalendarApplication.class, args);
	}

}
