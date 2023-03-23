package org.seek;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.seek.model.Advertisement;

import java.math.BigDecimal;

import static org.seek.RuleEngineService.*;

public class CheckoutServiceTest {
    private CheckoutService co;
    private Advertisement classicAD;
    private Advertisement standOutAD;
    private Advertisement premiumAD;

    @BeforeEach
    public void setUp() {
        co = new CheckoutServiceImpl();
        classicAD = new Advertisement("classic", BigDecimal.valueOf(269.99));
        standOutAD = new Advertisement("standOut", BigDecimal.valueOf(322.99));
        premiumAD = new Advertisement("premium", BigDecimal.valueOf(394.99));
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testTotal() {
        co.add(classicAD);
        BigDecimal expected = BigDecimal.valueOf(269.99);
        BigDecimal actual = co.total();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testTotalWithSecondBiteRules() {
        co.apply(SecondBiteRule);
        co.add(classicAD);
        co.add(classicAD);
        co.add(classicAD);
        co.add(classicAD);
        BigDecimal expected = BigDecimal.valueOf(809.97); // 269.99*3
        BigDecimal actual = co.total();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testTotalWithAxilCoffeeRules() {
        co.apply(AxilCoffeeRule);
        co.add(standOutAD);
        BigDecimal expected = BigDecimal.valueOf(299.99);
        BigDecimal actual = co.total();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testTotalWithMyerRule() {
        co.apply(MYERRule);
        co.add(standOutAD);
        co.add(standOutAD);
        co.add(standOutAD);
        co.add(standOutAD);
        co.add(standOutAD);
        co.add(premiumAD);
        BigDecimal expected = BigDecimal.valueOf(1681.95); // 322.99*4 + 389.99
        BigDecimal actual = co.total();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testScenarios1() {
        co.add(classicAD);
        co.add(standOutAD);
        co.add(premiumAD);
        BigDecimal expected = BigDecimal.valueOf(987.97);
        BigDecimal actual = co.total();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testScenarios2() {
        co.apply(SecondBiteRule);
        co.add(classicAD);
        co.add(classicAD);
        co.add(classicAD);
        co.add(premiumAD);
        BigDecimal expected = BigDecimal.valueOf(934.97);
        BigDecimal actual = co.total();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testScenarios3() {
        co.apply(AxilCoffeeRule);
        co.add(standOutAD);
        co.add(standOutAD);
        co.add(standOutAD);
        co.add(premiumAD);
        BigDecimal expected = BigDecimal.valueOf(1294.96);
        BigDecimal actual = co.total();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testWithInvalidRule() {
        co.apply("robert");
        co.add(standOutAD);
        co.add(standOutAD);
        co.add(standOutAD);
        BigDecimal expected = BigDecimal.valueOf(968.97);
        BigDecimal actual = co.total();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testWithMulitRules(){
        co.apply(SecondBiteRule);
        co.apply(AxilCoffeeRule);
        co.add(classicAD);
        co.add(classicAD);
        co.add(classicAD);
        co.add(standOutAD);
        BigDecimal expected = BigDecimal.valueOf(839.97);
        BigDecimal actual = co.total();
        Assertions.assertEquals(expected, actual);
    }
}
