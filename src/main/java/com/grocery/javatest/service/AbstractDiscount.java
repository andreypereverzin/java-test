package com.grocery.javatest.service;

import java.time.LocalDate;

public abstract class AbstractDiscount {
    private final LocalDate from;
    private final LocalDate to;

    protected AbstractDiscount(LocalDate from, LocalDate to) {
        this.from = from;
        this.to = to;
    }

    public boolean isApplicable(LocalDate date) {
        return date.compareTo(from) >= 0 && date.compareTo(to) <= 0;
    }
}
