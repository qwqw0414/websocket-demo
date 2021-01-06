package com.example.webchat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.webchat.model.ChatMessage;

@Controller
public class ChatController {

	@RequestMapping("/")
	public String home() {
		return "index";
	}
	
	@MessageMapping("/chat.sendMessage")
	@SendTo("/topic/public")
	public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
		return chatMessage;
	}

	@MessageMapping("/chat.addUser")
	@SendTo("/topic/public")
	public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
		headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
		return chatMessage;
	}

	/**
	 * 위에서 작성한 WebSocketConfig에서
	 * "/app"로 시작하는 대상이 있는 클라이언트에서 보낸 모든 메시지는 @MessageMapping 어노테이션이 달린
	 * 메서드로 라우팅 됩니다. 예를 들어 "/app/chat.sendMessage" 인 메세지는 sendMessage()로 라우팅 되며
	 * "/app/chat.addUser" 인 메시지는 addUser()로 라우팅됩니다.
	 */

}
