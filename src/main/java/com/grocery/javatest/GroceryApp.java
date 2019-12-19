package com.grocery.javatest;

import com.grocery.javatest.model.Item;
import com.grocery.javatest.service.ItemsProcessor;
import com.grocery.javatest.service.ItemsReader;

import java.util.List;

import static com.grocery.javatest.service.DiscountUtil.getAppleDiscount;
import static com.grocery.javatest.service.DiscountUtil.getSoupDiscount;

public class GroceryApp {
    private ItemsReader itemsReader;
    private ItemsProcessor itemsProcessor;

    public GroceryApp() {
        initialize();
    }

    public static void main(String[] args) {
        GroceryApp app = new GroceryApp();

        app.doJob();
    }

    private void initialize() {
        itemsReader = new ItemsReader();
        itemsProcessor = new ItemsProcessor();
        itemsProcessor.addDiscount(getSoupDiscount());
        itemsProcessor.addDiscount(getAppleDiscount());
    }

    private void doJob() {
        List<Item> items = itemsReader.readItems(System.in);
        double price = itemsProcessor.getPrice(items);
        System.out.println(price);
    }
}
