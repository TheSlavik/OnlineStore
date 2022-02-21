package org.example.store;

import org.example.domain.Category;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;

public class Store {

    private final List<Category> categories = new ArrayList<>();

    public Store() {
        try {
            for (Class<? extends Category> categoryClass
                    : new Reflections("org.example.domain").getSubTypesOf(Category.class)) {
                this.categories.add(categoryClass.getConstructor().newInstance());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Category> getCategories() {
        return categories;
    }
}
