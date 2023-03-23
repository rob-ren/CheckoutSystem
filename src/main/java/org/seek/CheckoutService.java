package org.seek;

import org.seek.model.Advertisement;

import java.math.BigDecimal;

public interface CheckoutService {

    public void apply(String pricingRule);

    public void add(Advertisement ads);

    public BigDecimal total();

}
