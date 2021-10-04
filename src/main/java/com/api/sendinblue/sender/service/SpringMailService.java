package com.api.sendinblue.sender.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.api.sendinblue.sender.dto.SimpleMailMessageDTO;
import com.api.sendinblue.sender.exception.BusinessException;
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
	public void sendSimpleMessage(SimpleMailMessageDTO message) throws BusinessException {
		SimpleMailMessage mailMessage = new SimpleMailMessage();

		try {

			final String from = !Utils.isEmptyOrNull(message.getFrom()) ? message.getFrom()
					: properties.getSenderDefault();

			mailMessage.setFrom(from);
			mailMessage.setTo(message.getTo());
			mailMessage.setSubject(message.getSubject());
			mailMessage.setText(message.getText());

			emailSender.send(mailMessage);
		} catch (Exception e) {
			throw new BusinessException("Erro on sending e-mail.");
		}

	}

	@Override
	public void sendHtmlMessage(String to, String subject, String htmlBody) throws BusinessException {

		MimeMessage message = emailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(htmlBody, true);

			emailSender.send(message);
		} catch (Exception e) {
			throw new BusinessException("Erro on sending e-mail.");
		}

	}

}
