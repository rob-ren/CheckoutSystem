package org.seek;

import org.seek.model.Advertisement;

import java.math.BigDecimal;
import java.util.ArrayList;

public class RuleEngineServiceImpl implements RuleEngineService {

    @Override
    public BigDecimal applySecondBiteRule(BigDecimal total, ArrayList<Advertisement> ads) {
        int classicAdsReset = 0;
        for (var ad : ads) {
            if (ad.getType() == "classic") {
                classicAdsReset++;
            }
            if (ad.getType() == "classic" && classicAdsReset == 3) {
                classicAdsReset = 0;
                total = total.subtract(ad.getPrice());
            }
        }
        return total;
    }

    @Override
    public BigDecimal applyAxilCoffeeRule(BigDecimal total, ArrayList<Advertisement> ads) {
        for (var ad : ads) {
            if (ad.getType() == "standOut") {
                total = total.subtract(BigDecimal.valueOf(23));
            }
        }
        return total;
    }

    @Override
    public BigDecimal applyMyerRule(BigDecimal total, ArrayList<Advertisement> ads) {
        int standOutAdsReset = 0;
        for (var ad : ads) {
            if (ad.getType() == "standOut") {
                standOutAdsReset++;
            }
            if (ad.getType() == "standOut" && standOutAdsReset == 5) {
                standOutAdsReset = 0;
                total = total.subtract(ad.getPrice());
            }
            if (ad.getType() == "premium") {
                total = total.subtract(BigDecimal.valueOf(5));
            }
        }
        return total;
    }
}
