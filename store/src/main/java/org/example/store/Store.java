package org.example.store;

import org.example.domain.Category;
import org.example.domain.Product;
import org.example.store.xml.XMLParser;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.stream.Collectors;

public class Store {

    public static Store store;
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
        if (store == null) {
            store = new Store();
        }
        return store;
    }

    public void generateProducts() {
        try {
            RandomStorePopulator populator = new RandomStorePopulator();
            Constructor<Product> constructor = Product.class.getConstructor(String.class, Integer.TYPE, Double.TYPE);
            for (Category category : getCategories()) {
                for (int i = 0; i < 5; i++) {
                    category.getProducts().add(constructor.newInstance(
                            populator.getName(category.getName()),
                            populator.getRate(),
                            populator.getPrice()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sort() {
        List<Product> list = getAllProducts();
        list.sort(new ProductComparator(XMLParser.getConfig()));
        System.out.println("\nSorted list");
        list.forEach(System.out::println);
        System.out.println();
    }

    public void top() {
        List<Product> list = getAllProducts();
        list.sort(new ProductComparator(Map.of("price", "desc")));
        System.out.println("\nTop 5");
        list.stream().limit(5).forEach(System.out::println);
        System.out.println();
    }

    private List<Product> getAllProducts() {
        return categories.stream()
                .map(Category::getProducts)
                .flatMap(List::stream)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public List<Category> getCategories() {
        return categories;
    }
}
