package org.example.store.order;

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
            time.sleep(new Random().nextLong(30) + 1);
            Store.getStore().getPurchased().addAll(products);
            System.out.println(currentThread().getName() + ": order processed.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
