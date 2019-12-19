package com.grocery.javatest.service;

import com.grocery.javatest.model.Item;

import java.math.BigDecimal;
import java.util.List;

public class ItemsProcessor {
    public double getPrice(List<Item> items) {
        double price = 0.0;

        for (Item item: items) {
            price += item.getProduct().getPrice().multiply(new BigDecimal(item.getAmount())).doubleValue();
        }

        return price;
    }
}
