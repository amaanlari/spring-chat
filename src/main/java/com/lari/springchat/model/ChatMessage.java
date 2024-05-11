package com.lari.springchat.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ChatMessage {
    private String message;
    private String sender;
    private MessageType type;
}
