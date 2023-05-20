package com.example.sample1app;

import javax.swing.JFrame;
import javax.swing.JLabel;

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
		app.setHeadless(false);
		app.run(args);
	}

	// @RequestMapping("/")
	// public String index(){
	// 	return "Hello, Spring boot3!";
	// }

	@Override
	public void run(String[] args) {
		JFrame frame = new JFrame("Spring Boot Swing App");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 200);
		frame.add(new JLabel("Spring Boot Application!"));
		frame.setVisible(true);

		System.out.println("+----------------------------------+");
		System.out.println("|This is CommandLine Runner Program|");
		System.out.println("+----------------------------------+");
		
	}
}
