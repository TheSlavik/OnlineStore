package org.example.store.populator;

import org.example.domain.Product;
import org.example.domain.categories.Category;
import org.example.store.util.ProductComparator;
import org.example.store.xml.XMLParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RandomStorePopulator implements Populator {

    private int total;

    @Override
    public void generateProducts() {
        for (Category category : store.getCategories()) {
            for (int i = 0; i < 10; i++) {
                Product product = generateProduct(category);
                product.setId(++total);
                category.getProducts().add(product);
            }
        }
    }

    @Override
    public void sort() {
        List<Product> list = getAllProducts();
        list.sort(new ProductComparator(XMLParser.getConfig("store/src/main/resources/sorting.xml")));
        list.forEach(System.out::println);
    }

    @Override
    public void top() {
        List<Product> list = getAllProducts();
        list.sort(new ProductComparator(Map.of("price", "desc")));
        list.stream().limit(5).forEach(System.out::println);
    }

    @Override
    public List<Product> getAllProducts() {
        return store.getCategories().stream()
                .map(Category::getProducts)
                .flatMap(List::stream)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public void removeProducts(List<Product> products) {
        store.getCategories().forEach(x -> products.forEach(y -> x.getProducts().remove(y)));
    }
}
