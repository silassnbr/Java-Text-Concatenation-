package com.example.javademo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@ComponentScan("com.example.javaDemo1")
@SpringBootApplication
@EnableMongoRepositories
public class javaDemo1Application {


	public static void main(String[] args) {
		SpringApplication.run(javaDemo1Application.class, args);

	}

}
