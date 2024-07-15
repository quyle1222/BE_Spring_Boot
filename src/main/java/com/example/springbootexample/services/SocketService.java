package com.example.springbootexample.services;

import com.corundumstudio.socketio.SocketIOClient;
import com.example.springbootexample.socket_config.Message;
import com.example.springbootexample.socket_config.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SocketService {

    private final MessageService messageService;

    public void sendSocketMessage(SocketIOClient senderClient, Message message, String room) {
        for (
                SocketIOClient client: senderClient.getNamespace().getRoomOperations(room).getClients()
        ) {
            if (!client.getSessionId().equals(senderClient.getSessionId())) {
                client.sendEvent("read_message", message);
            }
        }
    }

    public void saveMessage(SocketIOClient senderClient, Message message) {

        Message storedMessage = messageService.saveMessage(
                Message.builder()
                        .messageType(MessageType.CLIENT)
                        .message(message.getMessage())
                        .room(message.getRoom())
                        .username(message.getUsername())
                        .build()
        );

        sendSocketMessage(senderClient, storedMessage, message.getRoom());

    }

    public void saveInfoMessage(SocketIOClient senderClient, String message, String room) {
        Message storedMessage = messageService.saveMessage(
                Message.builder()
                        .messageType(MessageType.SERVER)
                        .message(message)
                        .room(room)
                        .build()
        );

        sendSocketMessage(senderClient, storedMessage, room);
    }
}