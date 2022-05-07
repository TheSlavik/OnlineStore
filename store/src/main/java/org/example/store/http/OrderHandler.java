package org.example.store.http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class OrderHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, 0);
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write("Order is ready!".getBytes(StandardCharsets.UTF_8));
        exchange.close();
    }
}
