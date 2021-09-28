package com.api.sendinblue.sender.mail;

import com.api.sendinblue.sender.dto.SimpleMailMessageDTO;

public interface MailService {
	void sendSimpleMessage(SimpleMailMessageDTO message);
}
