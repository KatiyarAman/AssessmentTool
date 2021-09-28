package com.sterp.multitenant.tenant.core.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.sterp.multitenant.tenant.dto.ChatMessage;
import com.sterp.multitenant.tenant.dto.Message;
import com.sterp.multitenant.tenant.dto.OutputMessage;

@Controller
public class ChatController {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@MessageMapping("/chat.sendMessage")
//	@SendTo("/topic/public")
	public void sendMessage(@Payload ChatMessage chatMessage) {
		simpMessagingTemplate.convertAndSendToUser("anshul", "/secured/user/queue/specific-user", chatMessage);
	}

	@MessageMapping("/chat.addUser")
	@SendTo("/topic/public")
	public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
		// Add username in web socket session
		headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
		return chatMessage;
	}

	@MessageMapping("/secured/room")
	public void sendSpecific(@Payload Message msg) throws Exception {
		OutputMessage out = new OutputMessage(msg.getFrom(), msg.getText(),
				new SimpleDateFormat("HH:mm").format(new Date()));
		simpMessagingTemplate.convertAndSendToUser(msg.getTo(), "/secured/user/queue/specific-user", out);
	}

}