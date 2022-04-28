package org.example.domain;

import org.example.domain.categories.Category;

public class Product {

    private final String name;
    private final int rate;
    private final double price;
    private final Category category;
    private int id;

    public Product(String name, int rate, double price, Category category) {
        this.name = name;
        this.rate = rate;
        this.price = price;
        this.category = category;
    }

    @Override
    public String toString() {
        return id + " - " + name + " [$" + price + ", " + rate + "*, " + category.getName() + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getRate() {
        return rate;
    }

    public double getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }
}
