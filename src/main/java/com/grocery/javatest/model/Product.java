package com.grocery.javatest.model;

import java.math.BigDecimal;
import java.util.Optional;

import static com.grocery.javatest.model.Unit.Bottle;
import static com.grocery.javatest.model.Unit.Loaf;
import static com.grocery.javatest.model.Unit.Single;
import static com.grocery.javatest.model.Unit.Tin;

public enum Product {
    Soup("soup", Tin, BigDecimal.valueOf(0.65)),
    Bread("bread", Loaf, BigDecimal.valueOf(0.80)),
    Milk("milk", Bottle, BigDecimal.valueOf(1.30)),
    Apple("apple", Single, BigDecimal.valueOf(0.10));

    private final String name;
    private final Unit unit;
    private final BigDecimal price;

    Product(String name, Unit unit, BigDecimal price) {
        this.name = name;
        this.unit = unit;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Unit getUnit() {
        return unit;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public static Optional<Product> findByName(String name) {
        for(Product product: values()) {
            if (name.equalsIgnoreCase(product.name)) {
                return Optional.of(product);
            }
        }

        return Optional.empty();
    }
}
