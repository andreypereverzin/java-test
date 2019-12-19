package com.grocery.javatest.service;

import com.grocery.javatest.model.Item;
import com.grocery.javatest.model.Product;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class ItemsProcessor {
    private final Set<Discount> discounts = new HashSet<>();

    public void addDiscount(Discount discount) {
        discounts.add(discount);
    }

    double getPrice(List<Item> items, Date date) {
        Map<Product, Double> normalizedItems = normalizeItems(items);
        BigDecimal price = BigDecimal.ZERO;

        for (Item item: items) {
            price = price.add(getItemPrice(item));
        }

        for (Discount discount: discounts) {
            if (discount.isApplicable(date)) {
                price = price.subtract(discount.getDiscount(normalizedItems));
            }
        }

        return price.doubleValue();
    }

    private Map<Product, Double> normalizeItems(List<Item> items) {
        Map<Product, Double> normalizedItems = new HashMap<>();
        for (Item item: items) {
            if (normalizedItems.containsKey(item.getProduct())) {
                normalizedItems.put(item.getProduct(),
                        normalizedItems.get(item.getProduct()) + item.getAmount());
            } else {
                normalizedItems.put(item.getProduct(), item.getAmount());
            }
        } return normalizedItems;
    }

    private BigDecimal getItemPrice(Item item) {
        return item.getProduct().getPrice().multiply(new BigDecimal(item.getAmount()));
    }
}
