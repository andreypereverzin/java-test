package com.grocery.javatest.service;

import com.grocery.javatest.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.List;

import static com.grocery.javatest.model.Product.Apple;
import static com.grocery.javatest.model.Product.Soup;
import static org.assertj.core.api.Assertions.assertThat;

class ItemsReaderTest {
    private ItemsReader itemsReader;

    @BeforeEach
    void setUp() {
        itemsReader = new ItemsReader();
    }

    @Test
    void readItems_should_read_one_item() {
        // given
        String line = "soup 1\n\n";

        // when
        List<Item> items = itemsReader.readItems(new ByteArrayInputStream(line.getBytes()));

        // then
        assertThat(items).hasSize(1);
        assertThat(items.get(0).getProduct()).isEqualTo(Soup);
        assertThat(items.get(0).getAmount()).isEqualTo(1);
    }

    @Test
    void readItems_should_read_one_item_without_amount() {
        // given
        String line = "soup\n\n";

        // when
        List<Item> items = itemsReader.readItems(new ByteArrayInputStream(line.getBytes()));

        // then
        assertThat(items).hasSize(1);
        assertThat(items.get(0).getProduct()).isEqualTo(Soup);
        assertThat(items.get(0).getAmount()).isEqualTo(1);
    }

    @Test
    void readItems_should_read_one_item_and_handlemultiple_spaces() {
        // given
        String line = "soup     1\n\n";

        // when
        List<Item> items = itemsReader.readItems(new ByteArrayInputStream(line.getBytes()));

        // then
        assertThat(items).hasSize(1);
        assertThat(items.get(0).getProduct()).isEqualTo(Soup);
        assertThat(items.get(0).getAmount()).isEqualTo(1);
    }

    @Test
    void readItems_should_read_amount_3() {
        // given
        String line = "soup 3\n\n";

        // when
        List<Item> items = itemsReader.readItems(new ByteArrayInputStream(line.getBytes()));

        // then
        assertThat(items).hasSize(1);
        assertThat(items.get(0).getProduct()).isEqualTo(Soup);
        assertThat(items.get(0).getAmount()).isEqualTo(3);
    }

    @Test
    void readItems_should_handle_empty_input() {
        // given
        String line = "\n";

        // when
        List<Item> items = itemsReader.readItems(new ByteArrayInputStream(line.getBytes()));

        // then
        assertThat(items).hasSize(0);
    }

    @Test
    void readItems_should_handle_wrong_product() {
        // given
        String line = "soub 3\n\n";

        // when
        List<Item> items = itemsReader.readItems(new ByteArrayInputStream(line.getBytes()));

        // then
        assertThat(items).hasSize(0);
    }

    @Test
    void readItems_should_handle_wrong_amount() {
        // given
        String line = "soup p\n\n";

        // when
        List<Item> items = itemsReader.readItems(new ByteArrayInputStream(line.getBytes()));

        // then
        assertThat(items).hasSize(0);
    }

    @Test
    void readItems_should_read_several_items() {
        // given
        String line = "soup 3\napple 4\n\n";

        // when
        List<Item> items = itemsReader.readItems(new ByteArrayInputStream(line.getBytes()));

        // then
        assertThat(items).hasSize(2);
        assertThat(items.get(0).getProduct()).isEqualTo(Soup);
        assertThat(items.get(0).getAmount()).isEqualTo(3);
        assertThat(items.get(1).getProduct()).isEqualTo(Apple);
        assertThat(items.get(1).getAmount()).isEqualTo(4);
    }
}
