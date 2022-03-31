package org.example.domain;

public class Product {

    private final String name;
    private final int rate;
    private final double price;

    public Product(String name, int rate, double price) {
        this.name = name;
        this.rate = rate;
        this.price = price;
    }

    public Product(Product product) {
        this.name = product.name;
        this.rate = product.rate;
        this.price = product.price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", rate=" + rate +
                ", price=" + price +
                '}';
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
}
