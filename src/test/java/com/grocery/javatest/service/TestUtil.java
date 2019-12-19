package com.grocery.javatest.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

final class TestUtil {
    private TestUtil() {
        //
    }

    static SoupDiscount getSoupDiscount() {
        LocalDate now = LocalDate.now();
        LocalDate from = now.minus(1, ChronoUnit.DAYS);
        LocalDate to = from.plus(7, ChronoUnit.DAYS);
        return new SoupDiscount(from, to);
    }

    static AppleDiscount getAppleDiscount() {
        LocalDate now = LocalDate.now();
        LocalDate from = now.plus(3, ChronoUnit.DAYS);
        LocalDate to = from.plus(1, ChronoUnit.MONTHS)
                .with(TemporalAdjusters.lastDayOfMonth());
        return new AppleDiscount(from, to);
    }
}
