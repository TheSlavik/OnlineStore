package org.example.store.util;

import org.example.domain.Product;
import org.example.domain.categories.BeerCategory;
import org.example.domain.categories.BookCategory;
import org.example.domain.categories.FruitCategory;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductComparatorTest {

    private final ProductComparator productComparator = new ProductComparator(Map.of("name", "asc"));
    private final Product product1 = new Product("Beer", 3, 96.28, new BeerCategory());
    private final Product product2 = new Product("Book", 5, 12.30, new BookCategory());
    private final Product product3 = new Product("Fruit", 1, 160.03, new FruitCategory());

    @Test
    void compare() {
        assertTrue(productComparator.compare(product1, product2) < 0);
        assertTrue(productComparator.compare(product2, product3) < 0);
        assertTrue(productComparator.compare(product3, product1) > 0);
    }

    @Test
    void config() {
        assertTrue(productComparator.config().containsKey("name"));
        assertTrue(productComparator.config().containsValue("asc"));
        assertFalse(productComparator.config().containsKey("NAME"));
        assertFalse(productComparator.config().containsValue("desc"));
    }
}