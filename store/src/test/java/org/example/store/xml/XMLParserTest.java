package org.example.store.xml;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class XMLParserTest {

    @Test
    void getConfig() {
        assertFalse(XMLParser.getConfig("src/main/resources/sorting.xml").isEmpty());
    }
}