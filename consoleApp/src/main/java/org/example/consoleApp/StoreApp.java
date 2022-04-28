package org.example.consoleApp;

import org.example.store.populator.DBPopulator;
import org.example.store.populator.Populator;
import org.example.store.populator.RandomStorePopulator;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class StoreApp {

    private final Populator populator = new DBPopulator();

    public static void main(String[] args) {
        StoreApp app = new StoreApp();
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
                        System.out.println("Enter the number(s) of desired product(s) or zero to cancel the order.");
                        app.populator.getAllProducts().forEach(System.out::println);
                        while (true) {
                            String order = reader.readLine().trim();
                            if (!"0".equals(order)) {
                                try {
                                    app.populator.createOrder(order);
                                } catch (Exception e) {
                                    System.out.println("Example: \"4 8 15\".");
                                    continue;
                                }
                            }
                            continue loop;
                        }
                    case "sort":
                        app.populator.sort();
                        break;
                    case "top":
                        app.populator.top();
                        break;
                    case "quit":
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
