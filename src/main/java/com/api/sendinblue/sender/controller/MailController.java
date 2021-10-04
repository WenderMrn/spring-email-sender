package com.api.sendinblue.sender.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.sendinblue.sender.dto.SimpleMailMessageDTO;
import com.api.sendinblue.sender.exception.BusinessException;
import com.api.sendinblue.sender.mail.MailService;
import com.api.sendinblue.sender.service.SpringMailService;

@RestController
@RequestMapping("/email")
public class MailController {

	
	private MailService mailService;
	
	@Autowired
	MailController(SpringMailService mailService){
		this.mailService = mailService;
	}

	@PostMapping("/send")
	public ResponseEntity<Map<String, Object>> sendSimpleMail(@Valid @RequestBody SimpleMailMessageDTO message,
			BindingResult result) {
		Map<String, Object> body = new HashMap<>();

		if (result.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
			result.getAllErrors().forEach(e -> {
				String fieldError = ((FieldError) e).getField();
				errors.put(fieldError, e.getDefaultMessage());
			});

			body.put("message", "Please check the parameters!");
			body.put("errors", errors);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
		}

		try {
			mailService.sendSimpleMessage(message);
			body.put("message", "Email successfully sent!");
			return ResponseEntity.ok(body);
		} catch (BusinessException e) {
			body.put("message", "Error on send email");
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
		}
	}

}
