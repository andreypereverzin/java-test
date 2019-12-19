package com.grocery.javatest.service;

import com.grocery.javatest.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.List;

import static com.grocery.javatest.model.Product.Soup;
import static org.assertj.core.api.Assertions.assertThat;

class ItemsReaderTest {
    private ItemsReader itemsReader;

    @BeforeEach
    void setUp() {
        itemsReader = new ItemsReader();
    }

    @Test
    @Disabled
    void readItems_should_read_one_item() {
        // given
        String line = "soup 1\n";

        // when
        List<Item> items = itemsReader.readItems(new ByteArrayInputStream(line.getBytes()));

        // then
        assertThat(items).containsExactlyInAnyOrder(new Item(Soup, 1));
    }
}