package org.example.store.orders;

import org.example.domain.Product;
import org.example.store.Store;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Order extends Thread {

    private final List<Product> products;
    private final TimeUnit time = TimeUnit.SECONDS;

    public Order(List<Product> products) {
        this.products = products;
    }

    @Override
    public void run() {
        try {
            Store.getStore().getCategories().forEach(x -> products.forEach(y -> x.getProducts().remove(y)));
            time.sleep(new Random().nextLong(30) + 1);
            Store.getStore().purchased.addAll(products);
            System.out.println(currentThread().getName() + ": order processed.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
