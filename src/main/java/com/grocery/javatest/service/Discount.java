package com.grocery.javatest.service;

import com.grocery.javatest.model.Product;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public interface Discount {
    default boolean isApplicable(Date date) {
        return false;
    }

    default BigDecimal getDiscount(Map<Product, Double> items) {
        return BigDecimal.ZERO;
    }
}
