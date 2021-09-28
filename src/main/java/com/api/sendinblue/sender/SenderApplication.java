package com.api.sendinblue.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.api.sendinblue.sender.service.SpringMailService;

@SpringBootApplication
public class SenderApplication implements CommandLineRunner {
	
	@Autowired
	private SpringMailService springMailService;

	public static void main(String[] args) {
		SpringApplication.run(SenderApplication.class, args);
	}

	//TODO: send test
	@Override
	public void run(String... args) throws Exception {
		springMailService.sendSimpleMessage("wender.mrn@gmail.com", "Test on Spring","Sent by Spring Boot Framework");
	}

}
