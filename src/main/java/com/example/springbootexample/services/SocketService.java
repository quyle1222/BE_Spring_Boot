package com.example.springbootexample.services;

import com.corundumstudio.socketio.SocketIOClient;
import com.example.springbootexample.models.Rooms;
import com.example.springbootexample.socket_config.Message;
import com.example.springbootexample.socket_config.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SocketService {

    private final RoomsServices roomsServices;
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
        UUID roomID = UUID.fromString(room);
//        Rooms isExist = roomsServices.getAll(roomID);

    }


    public void saveConnectedUserToRoom(SocketIOClient senderClient, String room, String username) {
        UUID roomID = UUID.fromString(room);
        List<Rooms> isExist = roomsServices.findAll();

        if (!isExist.isEmpty()) {
            UUID roomData = UUID.fromString(room);
        } else {
            UUID roomData = UUID.fromString(room);
        }
//        UUID roomID = UUID.fromString(room);
//        Optional<Rooms> isExist = roomsServices.findRoomById(roomID);
//
//        if (isExist.isPresent()) {
//            roomsServices.incrementConnectedCount(roomID);
//        } else {
//            Rooms roomData = new Rooms();
//            roomData.setRoomID(roomID);
//            roomData.setConnectedCount(1);
//            roomsServices.saveOrUpdateRoom(roomData);
//        }
//        for (SocketIOClient client: senderClient.getNamespace().getRoomOperations(room).getClients()) {
//            client.sendEvent("read_message", String.format("New user connected %s", username));
//        }
    }
}