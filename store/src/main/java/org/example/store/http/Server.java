package org.example.store.http;

import com.sun.net.httpserver.BasicAuthenticator;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {

    private HttpServer server;

    public void startServer() {
        try {
            server = HttpServer.create(new InetSocketAddress(80), 0);
            createContext("/categories", new CategoriesHandler());
            createContext("/order", new OrderHandler());
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createContext(String path, HttpHandler handler) {
        server.createContext(path, handler).setAuthenticator(new BasicAuthenticator("admin") {
            @Override
            public boolean checkCredentials(String username, String password) {
                return "admin".equals(username) && "admin".equals(password);
            }
        });
    }

    public HttpServer getServer() {
        return server;
    }
}
