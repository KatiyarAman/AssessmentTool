package com.sterp.multitenant.tenant.settings.smtp.dto;

import java.io.Serializable;

public class MailBody implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7473927929372198336L;
	private String recipient;
	private String subject;
	private String body;

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
}
