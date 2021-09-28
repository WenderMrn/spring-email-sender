package com.api.sendinblue.sender.property;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "smtp")
public class SmtpProperties {
	@NotNull
	private String senderDefault;
	
	@NotNull
	private String host;
	
	@NotNull
	private int port;
	
	@NotNull
	private String email;
	
	@NotNull
	private String password;

	public SmtpProperties() {
	}

	public String getSenderDefault() {
		return senderDefault;
	}

	public void setSenderDefault(String senderDefault) {
		this.senderDefault = senderDefault;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
