package com.example.sample1app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
// @RestController
public class SpringBootApp1Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SpringBootApp1Application.class);
		app.run(args);
	}

	// @RequestMapping("/")
	// public String index(){
	// 	return "Hello, Spring boot3!";
	// }

	@Override
	public void run(String[] args) {
		System.out.println("+----------------------------------+");
		System.out.println("|This is CommandLine Runner Program|");
		System.out.println("+----------------------------------+");
		
	}
}
