package org.example.consoleApp;

import org.example.domain.Product;
import org.example.store.Store;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

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
                        "create order" - choose desired products and buy them;
                        "sort" - sort all products according config and print them;
                        "top" - print the five most expensive products;
                        "quit" - exit app.""");
                switch (reader.readLine()) {
                    case "create order":
                        System.out.println("Enter the number(s) of desired product(s) or zero to cancel the order.");
                        List<Product> allProducts = app.store.getAllProducts();
                        allProducts.forEach(x -> System.out.printf("%2d. %s\n", allProducts.indexOf(x) + 1, x));
                        while (true) {
                            String order = reader.readLine().trim();
                            if (!"0".equals(order)) {
                                try {
                                    app.store.createOrder(order, allProducts);
                                } catch (NumberFormatException e) {
                                    System.out.println("Example: \"4 8 15\".");
                                    continue;
                                } catch (IndexOutOfBoundsException e) {
                                    System.out.println(e.getMessage());
                                    continue;
                                }
                            }
                            continue loop;
                        }
                    case "sort":
                        app.store.sort();
                        break;
                    case "top":
                        app.store.top();
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
