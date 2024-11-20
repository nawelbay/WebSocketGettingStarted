package org.example;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

import java.util.Scanner;

@ServerEndpoint("/chat") // Specify the WebSocket endpoint URI
public class ChatServerEndpoint {
    private static final Scanner scanner = new Scanner(System.in);
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Connected: " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("Received: " + message + " from " + session.getId());
        try {
            System.out.print("Enter a response: ");
            String response = scanner.nextLine();
            session.getBasicRemote().sendText("Server: " + response);
        } catch (Exception e) {
            System.err.println("Error sending message to client: " + e.getMessage());
        }
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Disconnected: " + session.getId());
    }
}
