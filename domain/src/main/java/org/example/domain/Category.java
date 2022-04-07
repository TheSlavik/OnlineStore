package org.example.domain;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Category {

    private final String name;
    private final List<Product> products;

    public Category(String name) {
        this.name = name;
        this.products = new CopyOnWriteArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return products;
    }
}
