package org.example.domain;

import org.example.domain.categories.BeerCategory;
import org.example.domain.categories.BookCategory;
import org.example.domain.categories.Category;
import org.example.domain.categories.FruitCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DomainTest {

    private final Category category = new BeerCategory();
    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product("Name", 3, 12.65, category);
    }

    @Test
    void testToString() {
        String expected = "0 - Name [$12.65, 3*, Beer]";
        assertEquals(product.toString(), expected);
    }

    @Test
    void getId() {
        assertEquals(product.getId(), 0);
        assertNotEquals(product.getId(), 10);
    }

    @Test
    void setId() {
        product.setId(5);
        assertEquals(product.getId(), 5);
        assertNotEquals(product.getId(), 1);
    }

    @Test
    void getName() {
        assertEquals(product.getName(), "Name");
        assertNotEquals(product.getName(), "name");
    }

    @Test
    void getRate() {
        assertEquals(product.getRate(), 3);
        assertNotEquals(product.getRate(), 4);
    }

    @Test
    void getPrice() {
        assertEquals(product.getPrice(), 12.65);
        assertNotEquals(product.getPrice(), 12.66);
    }

    @Test
    void getCategory() {
        assertEquals(product.getCategory(), category);
        assertNotEquals(product.getCategory(), new BookCategory());
        assertNotEquals(product.getCategory(), new FruitCategory());
    }

    @Test
    void check() {
        assertTrue(category.check("Beer"));
        assertFalse(category.check("Book"));
    }

    @Test
    void getProducts() {
        assertTrue(category.getProducts() instanceof CopyOnWriteArrayList<Product>);
        assertTrue(category.getProducts().isEmpty());
    }
}