package com.grocery.javatest.service;

import java.util.Date;

public abstract class AbstractDiscount {
    private final Date from;
    private final Date to;

    protected AbstractDiscount(Date from, Date to) {
        this.from = from;
        this.to = to;
    }

    public boolean isApplicable(Date date) {
        return date.compareTo(from) >= 0 && date.compareTo(to) <= 0;
    }
}
