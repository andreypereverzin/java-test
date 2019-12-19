package com.grocery.javatest.service;

import com.grocery.javatest.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.Map;

import static com.grocery.javatest.model.Product.Apple;
import static com.grocery.javatest.model.Product.Soup;
import static org.assertj.core.api.Assertions.assertThat;

class AppleDiscountTest {

    private AppleDiscount appleDiscount;

    @BeforeEach
    void setUp() {
        LocalDate now = LocalDate.now();
        LocalDate from = now.plus(3, ChronoUnit.DAYS);
        LocalDate to = from.plus(1, ChronoUnit.MONTHS)
                .with(TemporalAdjusters.lastDayOfMonth());
        appleDiscount = new AppleDiscount(from, to);
    }

    @Test
    void getDiscount_should_be_applicable_properly() {
        LocalDate now = LocalDate.now();
        LocalDate d1 = now.plus(2, ChronoUnit.DAYS);
        LocalDate d2 = now.plus(3, ChronoUnit.DAYS);
        LocalDate d3 = now.plus(1, ChronoUnit.MONTHS);
        LocalDate d4 = now.plus(2, ChronoUnit.MONTHS);
        assertThat(appleDiscount.isApplicable(now)).isFalse();
        assertThat(appleDiscount.isApplicable(d1)).isFalse();
        assertThat(appleDiscount.isApplicable(d2)).isTrue();
        assertThat(appleDiscount.isApplicable(d3)).isTrue();
        assertThat(appleDiscount.isApplicable(d4)).isFalse();
    }

    @Test
    void getDiscount_should_handle_no_apples() {
        // given
        Map<Product, Double> items = new HashMap<>();
        items.put(Soup, 2.0);

        // when
        BigDecimal discount = appleDiscount.getDiscount(items);

        // then
        assertThat(discount).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    void getDiscount_should_handle_single_apple() {
        // given
        Map<Product, Double> items = new HashMap<>();
        items.put(Soup, 2.0);
        items.put(Apple, 1.0);

        // when
        BigDecimal discount = appleDiscount.getDiscount(items);

        // then
        assertThat(discount).isEqualTo(Apple.getPrice().multiply(BigDecimal.valueOf(1))
                .divide(BigDecimal.TEN).setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    void getDiscount_should_handle_multiple_apples() {
        // given
        Map<Product, Double> items = new HashMap<>();
        items.put(Soup, 2.0);
        items.put(Apple, 4.0);

        // when
        BigDecimal discount = appleDiscount.getDiscount(items);

        // then
        assertThat(discount).isEqualTo(Apple.getPrice().multiply(BigDecimal.valueOf(4))
                .divide(BigDecimal.TEN).setScale(2, RoundingMode.HALF_UP));
    }
}
