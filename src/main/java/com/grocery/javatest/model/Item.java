package com.grocery.javatest.model;

public class Item {
    private final Product product;
    private final int amount;

    public Item(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public double getAmount() {
        return amount;
    }
}
