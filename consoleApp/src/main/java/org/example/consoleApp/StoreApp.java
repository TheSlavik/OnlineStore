package org.example.consoleApp;

import org.example.consoleApp.command.CreateOrderCommand;
import org.example.consoleApp.command.SortCommand;
import org.example.consoleApp.command.TopCommand;
import org.example.store.http.Client;
import org.example.store.http.Server;
import org.example.store.populator.DBPopulator;
import org.example.store.populator.Populator;
import org.example.store.populator.RandomStorePopulator;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class StoreApp {

    public static final Server server = new Server();
    public static final Client client = new Client();
    private final Populator populator = new RandomStorePopulator();

    public static void main(String[] args) {
        StoreApp app = new StoreApp();
        server.startServer();
        app.populator.generateProducts();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        loop:
        while (true) {
            try {
                System.out.println("""
                                                
                        Choose the option from listed below:
                        "create order" - choose desired products and buy them;
                        "sort" - sort all products according config and print them;
                        "top" - print the five most expensive products;
                        "quit" - exit app.""");
                switch (reader.readLine()) {
                    case "create order":
                        new CreateOrderCommand().execute(app.populator);
                        break;
                    case "sort":
                        new SortCommand().execute(app.populator);
                        break;
                    case "top":
                        new TopCommand().execute(app.populator);
                        break;
                    case "quit":
                        server.getServer().stop(0);
                        break loop;
                    default:
                        System.out.println("Unsupported operation.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
