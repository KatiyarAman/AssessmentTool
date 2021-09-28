package com.sterp.multitenant.tenant.dto;

import java.io.Serializable;

public class ChatMessage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5370431427715583682L;
	private MessageType type;
	private String content;
	private String sender;

	public enum MessageType {
		CHAT, JOIN, LEAVE
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}
}