package org.example.store.populator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PopulatorTest {

    private Populator populator;

    @BeforeEach
    void setUp() {
        populator = new RandomStorePopulator();
    }

    @AfterEach
    void tearDown() {
        populator = null;
    }

    @Test
    void generateProducts() {
        assertEquals(populator.getAllProducts().size(), 0);
        populator.generateProducts();
        assertTrue(populator.getAllProducts().size() > 0);
    }

    @Test
    void getAllProducts() {
        assertEquals(populator.getAllProducts().size(), 0);
    }

    @Test
    void removeProducts() {
        populator.generateProducts();
        populator.removeProducts(populator.getAllProducts());
        assertEquals(populator.getAllProducts().size(), 0);
    }
}