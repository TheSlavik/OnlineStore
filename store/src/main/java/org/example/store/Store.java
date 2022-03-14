package org.example.store;

import org.example.domain.Category;
import org.example.domain.Product;
import org.example.store.xml.XMLParser;
import org.reflections.Reflections;

import java.util.*;

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

    public void sort() {
        List<Product> list = getAllProducts();
        list.sort(new ProductComparator(XMLParser.getConfig()));
        System.out.println("\nSorted list");
        for (Product product : list) {
            System.out.println(product);
        }
        System.out.println();
    }

    public void top() {
        List<Product> list = getAllProducts();
        list.sort(new ProductComparator(Map.of("price", "desc")));
        System.out.println("\nTop 5");
        for (int i = 0; i < 5; i++) {
            System.out.println(list.get(i));
        }
        System.out.println();
    }

    private List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        for (Category category : categories) {
            for (Product product : category.getProducts()) {
                products.add(new Product(product));
            }
        }
        return products;
    }

    public List<Category> getCategories() {
        return categories;
    }
}
