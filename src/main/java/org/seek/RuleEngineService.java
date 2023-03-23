package org.seek;

import org.seek.model.Advertisement;

import java.math.BigDecimal;
import java.util.ArrayList;

public interface RuleEngineService {

    String SecondBiteRule = "SecondBite";
    String AxilCoffeeRule = "Axil Coffee Roasters";
    String MYERRule = "MYER";

    BigDecimal applySecondBiteRule(BigDecimal total, ArrayList<Advertisement> ads);

    BigDecimal applyAxilCoffeeRule(BigDecimal total, ArrayList<Advertisement> ads);

    BigDecimal applyMyerRule(BigDecimal total, ArrayList<Advertisement> ads);

}
