package org.example.domain;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private final String name;
    private final List<Product> products;

    public Category(String name) {
        this.name = name;
        this.products = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return products;
    }
}
