package org.seek.model;

import java.math.BigDecimal;

public class Advertisement {
    private String type;
    private BigDecimal price;

    public Advertisement(String type, BigDecimal price) {
        this.type = type;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
