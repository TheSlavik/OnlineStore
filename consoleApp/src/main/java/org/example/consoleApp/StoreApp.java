package org.example.consoleApp;

import org.example.domain.Category;
import org.example.domain.Product;
import org.example.store.RandomStorePopulator;
import org.example.store.Store;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;

public class StoreApp {

    private final Store store = new Store();

    public static void main(String[] args) {
        StoreApp app = new StoreApp();
        app.generateProducts();
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        loop:
        while (true) {
            try {
                System.out.println("""
                        Choose the option from listed below:
                        "sort" - sort all products according config and print them;
                        "top" - print the five most expensive products;
                        "quit" - exit app.
                        """);
                switch (r.readLine()) {
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

    public void generateProducts() {
        try {
            RandomStorePopulator r = new RandomStorePopulator();
            Constructor<Product> con = Product.class.getConstructor(String.class, Integer.TYPE, Double.TYPE);
            for (Category ctg : store.getCategories()) {
                for (int i = 0; i < 5; i++) {
                    ctg.getProducts().add(con.newInstance(r.getName(ctg.getName()), r.getRate(), r.getPrice()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printProducts() {
        for (Category category : store.getCategories()) {
            for (Product product : category.getProducts()) {
                System.out.println(product);
            }
        }
    }
}
