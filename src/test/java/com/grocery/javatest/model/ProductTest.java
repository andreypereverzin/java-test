package com.grocery.javatest.model;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.grocery.javatest.model.Product.Apple;
import static com.grocery.javatest.model.Product.Bread;
import static com.grocery.javatest.model.Product.Milk;
import static com.grocery.javatest.model.Product.Soup;
import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {

    @Test
    void findByName_should_return_products() {
        testProduct("soup", Soup);
        testProduct("Soup", Soup);
        testProduct("bread", Bread);
        testProduct("milk", Milk);
        testProduct("apple", Apple);
    }

    @Test
    void findByName_should_return_empty_if_wrong_name() {
        Optional<Product> res = Product.findByName("soub");

        assertThat(res.isPresent()).isFalse();
    }

    private void testProduct(String name, Product product) {
        Optional<Product> res = Product.findByName(name);

        assertThat(res.isPresent()).isTrue();
        assertThat(res.get()).isEqualTo(product);
    }
}