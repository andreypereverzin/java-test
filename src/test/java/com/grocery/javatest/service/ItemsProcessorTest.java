package com.grocery.javatest.service;

import com.grocery.javatest.model.Item;
import com.grocery.javatest.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static com.grocery.javatest.model.Product.Bread;
import static com.grocery.javatest.model.Product.Milk;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class ItemsProcessorTest {
    private ItemsProcessor itemsProcessor;

    @BeforeEach
    void setUp() {
        itemsProcessor = new ItemsProcessor();
    }

    @Test
    void getPrice_should_handle_empty_list() {
        // given
        List<Item> items = asList();

        // when
        double total = itemsProcessor.getPrice(items);

        // then
        assertThat(total).isEqualTo(0.0);
    }

    @Test
    void getPrice_should_handle_single_item() {
        // given
        List<Item> items = asList(new Item(Milk, 1));

        // when
        double total = itemsProcessor.getPrice(items);

        // then
        assertThat(total).isEqualTo(getPrice(Milk,1).doubleValue());
    }

    @Test
    void getPrice_should_handle_multiple_item() {
        // given
        List<Item> items = asList(new Item(Milk, 1),new Item(Bread, 1));

        // when
        double total = itemsProcessor.getPrice(items);

        // then
        assertThat(total).isEqualTo(getPrice(Milk,1).add(getPrice(Bread,1)).doubleValue());
    }

    private BigDecimal getPrice(Product product, double amount) {
        return product.getPrice().multiply(BigDecimal.valueOf(amount));
    }
}