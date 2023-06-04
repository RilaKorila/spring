package com.example.sample1app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@RestController
public class SpringBootApp1Application{
	final DataObject[] data = {
			new DataObject("noname", "no email adress", 0),
			new DataObject("taro", "taro@gmail.com", 35),
			new DataObject("hanako", "hanako@gmail.com", 23),
			new DataObject("jiro", "jiro@gmail.com", 10),
	};

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SpringBootApp1Application.class);
		app.run(args);
	}

	 @RequestMapping("/hello/")
	 public String index(){
	 	return "Hello, Spring boot3!";
	 }

	 @RequestMapping("/hello/{num}")
	 public DataObject index(@PathVariable int num){
		int n  = num < 0 ? 0: num > data.length ? 0: num;
		return data[n];
	 }
}

class DataObject{
	String name;
	String mail;
	int age;

	public DataObject(String name, String mail, int age){
		super();
		this.name = name;
		this.mail = mail;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(final String newName) {
		this.name = newName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(final String newMail) {
		this.mail = newMail;
	}

	public int getAge() {
		return age;
	}

	public void setAge(final int newAge) {
		this.age = newAge;
	}


}
