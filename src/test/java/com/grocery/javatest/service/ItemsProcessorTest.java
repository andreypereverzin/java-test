package com.grocery.javatest.service;

import com.grocery.javatest.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class ItemsProcessorTest {
    private ItemsProcessor itemsProcessor;

    @BeforeEach
    void setUp() {
        itemsProcessor = new ItemsProcessor();
    }

    @Test
    @Disabled
    void getPrice() {
        // given
        List<Item> items = asList();

        // when
        double total = itemsProcessor.getPrice(items);

        // then
        assertThat(total).isEqualTo(0.0);
    }
}