package com.grocery.javatest.service;

import com.grocery.javatest.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import static com.grocery.javatest.model.Product.Bread;
import static com.grocery.javatest.model.Product.Soup;
import static com.grocery.javatest.service.TestUtil.getSoupDiscount;
import static org.assertj.core.api.Assertions.assertThat;

class SoupDiscountTest {

    private SoupDiscount soupDiscount;

    @BeforeEach
    void setUp() {
        soupDiscount = getSoupDiscount();
    }

    @Test
    void getDiscount_should_be_applicable_properly() {
        LocalDate now = LocalDate.now();
        LocalDate d1 = now.minus(1, ChronoUnit.DAYS);
        LocalDate d2 = now.minus(2, ChronoUnit.DAYS);
        LocalDate d3 = now.plus(6, ChronoUnit.DAYS);
        LocalDate d4 = now.plus(7, ChronoUnit.DAYS);
        assertThat(soupDiscount.isApplicable(now)).isTrue();
        assertThat(soupDiscount.isApplicable(d1)).isTrue();
        assertThat(soupDiscount.isApplicable(d2)).isFalse();
        assertThat(soupDiscount.isApplicable(d3)).isTrue();
        assertThat(soupDiscount.isApplicable(d4)).isFalse();
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
                .setScale(2, BigDecimal.ROUND_HALF_UP));
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
                .setScale(2, BigDecimal.ROUND_HALF_UP));
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
                .setScale(2, BigDecimal.ROUND_HALF_UP));
    }
}