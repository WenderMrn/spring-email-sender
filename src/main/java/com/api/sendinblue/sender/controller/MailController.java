package com.api.sendinblue.sender.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.sendinblue.sender.dto.SimpleMailMessageDTO;
import com.api.sendinblue.sender.service.SpringMailService;

@RestController
@RequestMapping("/email")
public class MailController {

	@Autowired
	private SpringMailService springMailService;
	
	
	@PostMapping("/send")
	public ResponseEntity<Map<String, String>> sendSimpleMail(@Valid @RequestBody SimpleMailMessageDTO message, BindingResult result) {
		Map<String, String> body = new HashMap<>();
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach( r -> body.put("message", r.getDefaultMessage()));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
		}
        
		try {
			springMailService.sendSimpleMessage(message);
			body.put("message", "Email successfully sent!");
			return ResponseEntity.ok(body);
		}catch (MailException e) {
			body.put("message", "Error on send email");
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
		}
	}
	
}
