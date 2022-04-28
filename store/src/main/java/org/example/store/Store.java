package org.example.store;

import org.example.domain.categories.Category;
import org.example.domain.Product;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Store {

    private static final Store store = new Store();
    private final List<Product> purchased = new CopyOnWriteArrayList<>();
    private final List<Category> categories = new ArrayList<>();

    private Store() {
        try {
            for (Class<? extends Category> categoryClass
                    : new Reflections("org.example.domain").getSubTypesOf(Category.class)) {
                this.categories.add(categoryClass.getConstructor().newInstance());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Store getStore() {
        return store;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public List<Product> getPurchased() {
        return purchased;
    }
}
