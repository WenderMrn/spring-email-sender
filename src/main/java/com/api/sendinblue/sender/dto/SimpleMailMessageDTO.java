package com.api.sendinblue.sender.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

public class SimpleMailMessageDTO {
	@Nullable 
	private String from;
	
	@Nullable
	private String subject;
	
	@NotNull
	@NotBlank(message = "to can't blank")
	@Email(message = "to is not a valid e-mail") //TODO: create a custom validation
	private String to;
	
	@NotNull(message = "text is required")
	@NotBlank(message = "text can't blank")
	private String text;
	
	public SimpleMailMessageDTO() {}
	
	
	public SimpleMailMessageDTO(String from, String subject, String to, String text) {
		super();
		this.from = from;
		this.subject = subject;
		this.to = to;
		this.text = text;
	}

	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
}
