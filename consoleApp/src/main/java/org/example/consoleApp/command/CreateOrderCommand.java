package org.example.consoleApp.command;

import org.example.consoleApp.StoreApp;
import org.example.store.populator.Populator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CreateOrderCommand implements Command {

    @Override
    public void execute(Populator populator) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the number(s) of desired product(s) or zero to cancel the order.");
        populator.getAllProducts().forEach(System.out::println);
        while (true) {
            try {
                String order = reader.readLine().trim();
                if (!"0".equals(order)) {
                    try {
                        populator.createOrder(order);
                        StoreApp.client.createOrder();
                    } catch (Exception e) {
                        System.out.println("Example: \"4 8 15\".");
                        continue;
                    }
                }
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
