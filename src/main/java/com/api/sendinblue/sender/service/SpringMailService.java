package com.api.sendinblue.sender.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.api.sendinblue.sender.dto.SimpleMailMessageDTO;
import com.api.sendinblue.sender.mail.MailService;
import com.api.sendinblue.sender.property.SmtpProperties;
import com.api.sendinblue.sender.utils.Utils;

@Component
public class SpringMailService implements MailService {

	@Autowired
	private SmtpProperties properties;

	@Autowired
	private JavaMailSender emailSender;

	public SpringMailService() {
	}

	@Override
	public void sendSimpleMessage(SimpleMailMessageDTO message) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();

		final String from = !Utils.isEmptyOrNull(message.getFrom()) ? message.getFrom() : properties.getSenderDefault();

		mailMessage.setFrom(from);
		mailMessage.setTo(message.getTo());
		mailMessage.setSubject(message.getSubject());
		mailMessage.setText(message.getText());

		emailSender.send(mailMessage);
	}

}
