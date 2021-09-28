package com.api.sendinblue.sender.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.api.sendinblue.sender.mail.MailService;
import com.api.sendinblue.sender.property.SmtpProperties;

@Component
public class SpringMailService implements MailService {

	@Autowired
	private SmtpProperties properties;

	@Autowired
	private JavaMailSender emailSender;

	public SpringMailService () {
	}

	@Override
	public void sendSimpleMessage(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom(properties.getSenderDefault());
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);

		emailSender.send(message);
	}

}
