package org.example.store.orders;

import org.example.store.Store;

import java.util.concurrent.TimeUnit;

public class OrdersCleaner extends Thread {

    public static final OrdersCleaner ordersCleaner = new OrdersCleaner();

    static {
        ordersCleaner.setDaemon(true);
        ordersCleaner.setName("Cleaner");
    }

    public static OrdersCleaner getOrdersCleaner() {
        return ordersCleaner;
    }

    @Override
    public void run() {
        while (isAlive()) {
            try {
                TimeUnit.MINUTES.sleep(1);
                Store.getStore().purchased.clear();
                System.out.println(currentThread().getName() + ": orders cleared.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
