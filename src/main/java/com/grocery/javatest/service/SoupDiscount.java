package com.grocery.javatest.service;

import com.grocery.javatest.model.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Map;

import static com.grocery.javatest.model.Product.Bread;
import static com.grocery.javatest.model.Product.Soup;

public class SoupDiscount extends AbstractDiscount implements Discount {

    protected SoupDiscount(LocalDate from, LocalDate to) {
        super(from, to);
    }

    public BigDecimal getDiscount(Map<Product, Double> items) {
        if (!items.containsKey(Bread) || !items.containsKey(Soup)) {
            return BigDecimal.ZERO;
        }

        int soupsForDiscount = (int)(items.get(Soup) / 2);

        double loafsForDiscount = items.get(Bread) <= soupsForDiscount ? items.get(Bread) : soupsForDiscount;

        return Bread.getPrice()
                .divide(BigDecimal.valueOf(2))
                .multiply(BigDecimal.valueOf(loafsForDiscount))
                .setScale(2, RoundingMode.HALF_UP);
    }
}
