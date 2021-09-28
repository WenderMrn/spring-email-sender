package com.api.sendinblue.sender.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.api.sendinblue.sender.property.SmtpProperties;

@Configuration
public class MailConfig {
	
	private SmtpProperties properties;
	
	public MailConfig(@Autowired SmtpProperties properties) {
		this.properties = properties;
	}

	@Bean
	public JavaMailSender getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    
	    mailSender.setHost(properties.getHost());
	    mailSender.setPort(properties.getPort());
	    mailSender.setUsername(properties.getEmail());
	    mailSender.setPassword(properties.getPassword());
	    
	    Properties props = mailSender.getJavaMailProperties();
	    
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	    
	    return mailSender;
	}
	
}
