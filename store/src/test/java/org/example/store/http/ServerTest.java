package org.example.store.http;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServerTest {

    private final Server server = new Server();

    @Test
    void startServer() {
        server.startServer();
        assertEquals(server.getServer().getAddress().getPort(), 80);
    }
}