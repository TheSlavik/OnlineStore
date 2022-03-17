package org.example.store;

import org.example.domain.Product;

import java.util.Comparator;
import java.util.Map;

public class ProductComparator implements Comparator<Product> {

    private final Map<String, String> config;

    public ProductComparator(Map<String, String> config) {
        this.config = config;
    }

    @Override
    public int compare(Product o1, Product o2) {
        int result = 0;
        for (Map.Entry<String, String> entry : config.entrySet()) {
            switch (entry.getKey()) {
                case "name" -> result = o1.getName().compareTo(o2.getName());
                case "rate" -> result = o1.getRate() - o2.getRate();
                case "price" -> {
                    if (o1.getPrice() > o2.getPrice()) {
                        result = 1;
                    } else if (o1.getPrice() < o2.getPrice()) {
                        result = -1;
                    }
                }
            }
            if (result != 0) {
                if (entry.getValue().equals("desc")) {
                    result = Math.negateExact(result);
                }
                break;
            }
        }
        return result;
    }
}
