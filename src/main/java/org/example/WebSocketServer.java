package org.example;

import org.glassfish.tyrus.server.Server;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class WebSocketServer {

    public static void main(String[] args) {
        // Configuration Map (can be empty for most simple use cases)
        Map<String, Object> serverProperties = new HashMap<>();
        // Initialize the Tyrus Server with required parameters
        Server server = new Server("localhost", 8080, "/", serverProperties, Collections.singleton(ChatServerEndpoint.class));

        try {
            server.start();
            System.out.println("WebSocket server started on ws://localhost:8080/chat");
            // Keep the server running indefinitely
            synchronized (WebSocketServer.class) {
                WebSocketServer.class.wait();
            }
                System.in.read(); // Wait for user input to stop the server


        } catch (Exception e) {
            System.err.println("Server error: " + e.getMessage());
        } finally {
            server.stop();
            System.out.println("Server stopped.");
        }
    }
}
