package iuh.fit.se.lab2_websocket;

import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint("/chatEndpoint/{username}")
public class ChatServerEndpoint {

    private static Set<Session> chatters = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) throws IOException {
        session.getUserProperties().put("username", username);
        chatters.add(session);
        // Gửi thông báo đến tất cả các client
        broadcast(username + " joined the chat.");
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        String username = (String) session.getUserProperties().get("username");
        broadcast(username + ": " + message);
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        String username = (String) session.getUserProperties().get("username");
        chatters.remove(session);
        broadcast(username + " left the chat.");
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Xử lý lỗi
        System.err.println("Error on session " + session.getId() + ": " + throwable.getMessage());
    }

    private static void broadcast(String message) throws IOException {
        for (Session session : chatters) {
            session.getBasicRemote().sendText(message);
        }
    }
}