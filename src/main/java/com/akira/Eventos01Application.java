package com.akira;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.akira.controller")
@EnableJpaRepositories("com.akira.repository")
@EntityScan("com.akira.model")
public class Eventos01Application {

	public static void main(String[] args) {
		SpringApplication.run(Eventos01Application.class, args);
	}

}
