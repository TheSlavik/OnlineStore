package org.example.store;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StoreTest {

    private final Store store = Store.getStore();

    @Test
    void getStore() {
        assertEquals(store, Store.getStore());
    }
}