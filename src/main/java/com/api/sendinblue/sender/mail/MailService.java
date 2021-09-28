package com.api.sendinblue.sender.mail;

public interface MailService {
	void sendSimpleMessage(String to, String subject, String text);
}
