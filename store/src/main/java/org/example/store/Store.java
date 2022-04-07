package org.example.store;

import org.example.domain.Category;
import org.example.domain.Product;
import org.example.store.orders.Order;
import org.example.store.orders.OrdersCleaner;
import org.example.store.xml.XMLParser;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class Store {

    public static final Store store = new Store();
    private final List<Category> categories = new ArrayList<>();
    public final List<Product> purchased = new CopyOnWriteArrayList<>();

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

    public void createOrder(String order, List<Product> allProducts) throws IndexOutOfBoundsException {
        List<Integer> orderNumbers = Arrays.stream(order.split("\\W+"))
                .map(Integer::parseInt)
                .toList();
        orderNumbers.forEach(x -> {
            if (x > allProducts.size() || x < 1) {
                throw new IndexOutOfBoundsException("Selected product(s) doesn't exist.");
            }
        });
        List<Product> orderedProducts = orderNumbers.stream()
                .map(x -> x - 1)
                .map(allProducts::get)
                .toList();
        new Order(orderedProducts).start();
        System.out.println("Order is processing...");
        if (!OrdersCleaner.getOrdersCleaner().isAlive()) {
            OrdersCleaner.getOrdersCleaner().start();
        }
    }

    public void sort() {
        List<Product> list = getAllProducts();
        list.sort(new ProductComparator(XMLParser.getConfig()));
        list.forEach(System.out::println);
    }

    public void top() {
        List<Product> list = getAllProducts();
        list.sort(new ProductComparator(Map.of("price", "desc")));
        list.stream().limit(5).forEach(System.out::println);
    }

    public List<Product> getAllProducts() {
        return categories.stream()
                .map(Category::getProducts)
                .flatMap(List::stream)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public List<Category> getCategories() {
        return categories;
    }
}
