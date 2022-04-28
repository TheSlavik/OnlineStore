package org.example.store.populator;

import com.github.javafaker.Faker;
import org.example.domain.categories.Category;
import org.example.domain.Product;
import org.example.store.Store;
import org.example.store.order.Order;
import org.example.store.order.OrdersCleaner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface Populator {

    Store store = Store.getStore();
    Faker faker = new Faker();

    default Product generateProduct(Category category) {
        return new Product(getName(category.getName()), getRate(), getPrice(), category);
    }

    void generateProducts();

    default void createOrder(String order) {
        List<Product> orderedProducts = new ArrayList<>();
        Arrays.stream(order.split("\\W+")).map(Integer::parseInt).forEach(x -> {
            for (Product product : getAllProducts()) {
                if (product.getId() == x) {
                    orderedProducts.add(product);
                }
            }
        });
        new Order(orderedProducts).start();
        removeProducts(orderedProducts);
        System.out.println("Order is processing...");
        if (!OrdersCleaner.getOrdersCleaner().isAlive()) {
            OrdersCleaner.getOrdersCleaner().start();
        }

    }

    void sort();

    void top();

    List<Product> getAllProducts();

    void removeProducts(List<Product> products);

    default String getName(String name) {
        return switch (name) {
            case "Beer":
                yield faker.beer().name();
            case "Book":
                yield faker.book().title();
            case "Fruit":
                yield faker.food().fruit();
            default:
                yield faker.letterify("?" + faker.lorem().word(), true);
        };
    }

    default int getRate() {
        return faker.number().numberBetween(1, 6);
    }

    default double getPrice() {
        return faker.number().randomDouble(2, 0, 100);
    }
}
