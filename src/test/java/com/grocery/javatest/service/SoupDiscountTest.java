package com.grocery.javatest.service;

import com.grocery.javatest.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.grocery.javatest.model.Product.Bread;
import static com.grocery.javatest.model.Product.Soup;
import static org.assertj.core.api.Assertions.assertThat;

class SoupDiscountTest {

    private SoupDiscount soupDiscount;

    @BeforeEach
    void setUp() {
        Instant now = Instant.now();
        Instant from = now.minus(1, ChronoUnit.DAYS);
        Instant to = from.plus(7, ChronoUnit.DAYS);
        soupDiscount = new SoupDiscount(Date.from(from), Date.from(to));
    }

    @Test
    void getDiscount_should_be_applicable_properly() {
        Instant now = Instant.now();
        Instant d1 = now.minus(2, ChronoUnit.DAYS);
        Instant d2 = now.minus(8, ChronoUnit.DAYS);
        assertThat(soupDiscount.isApplicable(new Date())).isTrue();
        assertThat(soupDiscount.isApplicable(Date.from(d1))).isFalse();
        assertThat(soupDiscount.isApplicable(Date.from(d2))).isFalse();
    }

    @Test
    void getDiscount_should_handle_simple_case() {
        // given
        Map<Product, Double> items = new HashMap<>();
        items.put(Soup, 2.0);
        items.put(Bread, 1.0);

        // when
        BigDecimal discount = soupDiscount.getDiscount(items);

        // then
        assertThat(discount).isEqualTo(Bread.getPrice().divide(BigDecimal.valueOf(2))
                .setScale(2, BigDecimal.ROUND_UP));
    }

    @Test
    void getDiscount_should_handle_3_soups_2_breads() {
        // given
        Map<Product, Double> items = new HashMap<>();
        items.put(Soup, 3.0);
        items.put(Bread, 2.0);

        // when
        BigDecimal discount = soupDiscount.getDiscount(items);

        // then
        assertThat(discount).isEqualTo(Bread.getPrice().divide(BigDecimal.valueOf(2))
                .setScale(2, BigDecimal.ROUND_UP));
    }

    @Test
    void getDiscount_should_handle_4_soups_2_breads() {
        // given
        Map<Product, Double> items = new HashMap<>();
        items.put(Soup, 4.0);
        items.put(Bread, 2.0);

        // when
        BigDecimal discount = soupDiscount.getDiscount(items);

        // then
        assertThat(discount).isEqualTo(Bread.getPrice()
                .divide(BigDecimal.valueOf(2)).multiply(BigDecimal.valueOf(2))
                .setScale(2, BigDecimal.ROUND_UP));
    }
}