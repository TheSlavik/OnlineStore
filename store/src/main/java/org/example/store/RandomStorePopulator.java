package org.example.store;

import com.github.javafaker.Faker;

public class RandomStorePopulator {

    private final Faker faker;

    public RandomStorePopulator() {
        this.faker = new Faker();
    }

    public String getName(String name) {
        return switch (name) {
            case "Bike":
                yield faker.pokemon().name();
            case "Milk":
                yield faker.beer().name();
            case "Phone":
                yield faker.commerce().productName();
            default:
                yield faker.letterify("?" + faker.lorem().word(), true);
        };
    }

    public int getRate() {
        return faker.number().numberBetween(1, 6);
    }

    public double getPrice() {
        return faker.number().randomDouble(2, 0, 100);
    }
}
