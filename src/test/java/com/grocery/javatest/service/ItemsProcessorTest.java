package com.grocery.javatest.service;

import com.grocery.javatest.model.Item;
import com.grocery.javatest.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static com.grocery.javatest.model.Product.Apple;
import static com.grocery.javatest.model.Product.Bread;
import static com.grocery.javatest.model.Product.Milk;
import static com.grocery.javatest.model.Product.Soup;
import static com.grocery.javatest.service.DiscountUtil.getAppleDiscount;
import static com.grocery.javatest.service.DiscountUtil.getSoupDiscount;
import static java.time.LocalDate.now;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

class ItemsProcessorTest {
    private ItemsProcessor itemsProcessor;
    private SoupDiscount soupDiscount;
    private AppleDiscount appleDiscount;

    @BeforeEach
    void setUp() {
        itemsProcessor = new ItemsProcessor();
        soupDiscount = getSoupDiscount();
        appleDiscount = getAppleDiscount();
    }

    @Test
    void getPrice_should_handle_empty_list() {
        // given
        List<Item> items = emptyList();

        // when
        double total = itemsProcessor.getPrice(items, now());

        // then
        assertThat(total).isEqualTo(0.0);
    }

    @Test
    void getPrice_should_handle_single_item() {
        // given
        List<Item> items = singletonList(new Item(Milk, 1));

        // when
        double total = itemsProcessor.getPrice(items, now());

        // then
        assertThat(total).isEqualTo(getPrice(Milk, 1).doubleValue());
    }

    @Test
    void getPrice_should_handle_multiple_items() {
        // given
        List<Item> items = asList(new Item(Milk, 1), new Item(Bread, 1));

        // when
        double total = itemsProcessor.getPrice(items, now());

        // then
        assertThat(total).isEqualTo(getPrice(Milk, 1).add(getPrice(Bread, 1)).doubleValue());
    }

    @Test
    void getPrice_should_apply_discounts_3_soups_2_breads_today() {
        // given
        itemsProcessor.addDiscount(soupDiscount);
        itemsProcessor.addDiscount(appleDiscount);
        List<Item> items = asList(new Item(Soup, 3), new Item(Bread, 2));

        // when
        double total = itemsProcessor.getPrice(items, now());

        // then
        assertThat(total).isEqualTo(3.15);
    }

    @Test
    void getPrice_should_apply_discounts_6_apples_1_milk_today() {
        // given
        itemsProcessor.addDiscount(soupDiscount);
        itemsProcessor.addDiscount(appleDiscount);
        List<Item> items = asList(new Item(Apple, 6), new Item(Milk, 1));

        // when
        double total = itemsProcessor.getPrice(items, now());

        // then
        assertThat(total).isEqualTo(1.9);
    }

    @Test
    void getPrice_should_apply_discounts_6_apples_1_milk_in_5_days() {
        // given
        itemsProcessor.addDiscount(soupDiscount);
        itemsProcessor.addDiscount(appleDiscount);
        List<Item> items = asList(new Item(Apple, 6), new Item(Milk, 1));

        // when
        double total = itemsProcessor.getPrice(items, now().plus(5, DAYS));

        // then
        assertThat(total).isEqualTo(1.84);
    }

    @Test
    void getPrice_should_apply_discounts_3_apples_2_soup_1_bread_in_5_days() {
        // given
        itemsProcessor.addDiscount(soupDiscount);
        itemsProcessor.addDiscount(appleDiscount);
        List<Item> items = asList(new Item(Apple, 3), new Item(Soup, 2), new Item(Bread, 1));

        // when
        double total = itemsProcessor.getPrice(items, now().plus(5, DAYS));

        // then
        assertThat(total).isEqualTo(1.97);
    }

    private BigDecimal getPrice(Product product, double amount) {
        return product.getPrice().multiply(BigDecimal.valueOf(amount));
    }
}