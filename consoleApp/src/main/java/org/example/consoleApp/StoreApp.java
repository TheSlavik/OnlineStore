package org.example.consoleApp;

import org.example.store.Store;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class StoreApp {

    private final Store store = Store.getStore();

    public static void main(String[] args) {
        StoreApp app = new StoreApp();
        app.store.generateProducts();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        loop:
        while (true) {
            try {
                System.out.println("""
                        Choose the option from listed below:
                        "sort" - sort all products according config and print them;
                        "top" - print the five most expensive products;
                        "quit" - exit app.
                        """);
                switch (reader.readLine()) {
                    case "sort":
                        app.store.sort();
                        break;
                    case "top":
                        app.store.top();
                        break;
                    case "quit":
                        break loop;
                    default:
                        System.out.println("\nUnsupported operation.\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
