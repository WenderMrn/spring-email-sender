package com.api.sendinblue.sender.mail;

import com.api.sendinblue.sender.dto.SimpleMailMessageDTO;
import com.api.sendinblue.sender.exception.BusinessException;

public interface MailService {
	void sendSimpleMessage(SimpleMailMessageDTO message) throws BusinessException;
	void sendHtmlMessage(SimpleMailMessageDTO message) throws BusinessException;
}
