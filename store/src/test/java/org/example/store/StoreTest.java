package org.example.store;

import org.example.domain.Product;
import org.example.domain.categories.Category;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StoreTest {

    private final Store store = Store.getStore();

    @Test
    void getStore() {
        assertEquals(Store.getStore(), store);
    }

    @Test
    void getCategories() {
        assertTrue(store.getCategories() instanceof ArrayList<Category>);
        assertFalse(store.getCategories().isEmpty());
    }

    @Test
    void getPurchased() {
        assertTrue(store.getPurchased() instanceof CopyOnWriteArrayList<Product>);
        assertTrue(store.getPurchased().isEmpty());
    }
}