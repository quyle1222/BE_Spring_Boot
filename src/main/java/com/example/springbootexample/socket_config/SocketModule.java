package com.example.springbootexample.socket_config;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.example.springbootexample.services.SocketService;
import com.example.springbootexample.utils.AppConstants;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Slf4j
@Component
public class SocketModule {

    private final SocketIOServer server;

    private final SocketService socketService;

    public SocketModule(SocketIOServer server, SocketService socketService) {
        this.server = server;
        this.socketService = socketService;
        server.addConnectListener(this.onConnected());
        server.addDisconnectListener(this.onDisconnected());
        server.addEventListener(AppConstants.sendMessageEvent, Object.class, this.onChatReceived());
    }

    private DataListener<Object> onChatReceived() {
        return (senderClient, data, ackSender) -> {
            log.info(data.toString());
        };
    }

    private ConnectListener onConnected() {
        return (client) -> {
            var params = client.getHandshakeData().getUrlParams();
            String room = String.join("", params.get("room"));
            String username = String.join("", params.get("username"));
            if (!room.isEmpty() && !username.isEmpty()) {
                client.joinRoom(room);
                socketService.saveConnectedUserToRoom(client, room, username);
            }
        };

    }

    private DisconnectListener onDisconnected() {
        return client -> {
            var params = client.getHandshakeData().getUrlParams();
            String room = String.join("", params.get("room"));
            String username = String.join("", params.get("username"));
            if (!room.isEmpty() && !username.isEmpty()) {
                client.leaveRoom(room);
                socketService.saveInfoMessage(client, String.format("%s disconnected", username), room);
            }
        };
    }
}