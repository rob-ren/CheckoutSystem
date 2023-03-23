package org.seek;

import org.seek.model.Advertisement;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.seek.RuleEngineService.*;

public class CheckoutServiceImpl implements CheckoutService {
    private final ArrayList<Advertisement> totalAds = new ArrayList<>();
    private final ArrayList<String> pricingRules = new ArrayList<>();

    private final RuleEngineServiceImpl ruleEngineService = new RuleEngineServiceImpl();

    @Override
    public void apply(String pricingRule) {
        pricingRules.add(pricingRule);
    }

    @Override
    public void add(Advertisement advertisement) {
        totalAds.add(advertisement);
    }

    @Override
    public BigDecimal total() {
        BigDecimal total = totalAds.stream().map(ad -> ad.getPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);

        if (pricingRules.contains(SecondBiteRule)) {
            total = ruleEngineService.applySecondBiteRule(total, totalAds);
        }
        if (pricingRules.contains(AxilCoffeeRule)) {
            total = ruleEngineService.applyAxilCoffeeRule(total, totalAds);
        }
        if (pricingRules.contains(MYERRule)) {
            total = ruleEngineService.applyMyerRule(total, totalAds);
        }
        return total;
    }
}
