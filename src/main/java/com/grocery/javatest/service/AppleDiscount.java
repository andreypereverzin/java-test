package com.grocery.javatest.service;

import com.grocery.javatest.model.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Map;

import static com.grocery.javatest.model.Product.Apple;

public class AppleDiscount extends AbstractDiscount implements Discount {

    protected AppleDiscount(LocalDate from, LocalDate to) {
        super(from, to);
    }

    public BigDecimal getDiscount(Map<Product, Double> items) {
        if (!items.containsKey(Apple)) {
            return BigDecimal.ZERO;
        }

        return Apple.getPrice()
                .divide(BigDecimal.TEN)
                .multiply(BigDecimal.valueOf(items.get(Apple)))
                .setScale(2, RoundingMode.HALF_UP);
    }
}
