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
	public void sendHtmlMessage(SimpleMailMessageDTO message) throws BusinessException {

		MimeMessage mailMessage = emailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true, "UTF-8");

			helper.setTo(message.getTo());
			helper.setSubject(message.getSubject());
			helper.setText(message.getText(), true);

			emailSender.send(mailMessage);
		} catch (Exception e) {
			throw new BusinessException("Erro on sending e-mail.");
		}

	}
}
