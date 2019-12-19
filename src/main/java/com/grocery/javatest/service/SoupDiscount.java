package com.grocery.javatest.service;

import com.grocery.javatest.model.Product;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import static com.grocery.javatest.model.Product.Bread;
import static com.grocery.javatest.model.Product.Soup;

public class SoupDiscount extends AbstractDiscount implements Discount {

    protected SoupDiscount(Date from, Date to) {
        super(from, to);
    }

    public BigDecimal getDiscount(Map<Product, Double> items) {
        if (!items.containsKey(Bread)) {
            return BigDecimal.ZERO;
        }

        int soupsForDiscount = (int)(items.get(Soup) / 2);

        double loafsForDiscount = items.get(Bread) <= soupsForDiscount ? items.get(Bread) : soupsForDiscount;

        return Bread.getPrice()
                .divide(BigDecimal.valueOf(2))
                .multiply(BigDecimal.valueOf(loafsForDiscount));
    }
}
