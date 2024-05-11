package com.lari.springchat.controller;

import com.lari.springchat.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    /**
     * Handles the sending of chat messages.
     *
     * @param chatMessage The chat message payload.
     * @return The same chat message that was sent.
     *
     */
    @MessageMapping("chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    /**
     * Handles the addition of a user to the chat session.
     *
     * @param chatMessage The chat message payload, which includes the user's information.
     * @param headerAccessor Accessor to the headers of the message, used to store the user's session information.
     * @return The same chat message that was received, confirming the user's addition.
     *
     * <p>
     *     This method is responsible for adding a user's username to the WebSocket session attributes.
     *     This allows the application to keep track of the user during their chat session.
     * </p>
     */
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {

        // Adds username in web socket's session.
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}
