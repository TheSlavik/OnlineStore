package org.example.domain.categories;

import org.example.domain.Product;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Category {

    private final String name;
    private final List<Product> products;

    public Category(String name) {
        this.name = name;
        this.products = new CopyOnWriteArrayList<>();
    }

    public boolean check(String s) {
        return name.equals(s);
    }

    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return products;
    }
}
