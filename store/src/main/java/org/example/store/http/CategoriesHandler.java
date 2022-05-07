package org.example.store.http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.store.Store;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class CategoriesHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        Store.getStore().getCategories().forEach(x -> stringBuilder.append(x.getName()).append(" "));
        byte[] bytes = stringBuilder.toString().trim().getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(200, bytes.length);
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(bytes);
        exchange.close();
    }
}
