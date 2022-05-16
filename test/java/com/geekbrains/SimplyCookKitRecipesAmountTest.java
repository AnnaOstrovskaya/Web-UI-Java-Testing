package com.geekbrains;

import com.geekbrains.lesson9.SimplyCookTesterComplex;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@Nested
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SimplyCookKitRecipesAmountTest {

    private static Logger logger = LoggerFactory.getLogger("SimplyCookTests");
    public SimplyCookTesterComplex simplyCookTester;

    @BeforeAll
    void beforeAll() {
        System.out.println("Initialising SimplyCookTester..");
        this.simplyCookTester = new SimplyCookTesterComplex();
    }

    @BeforeEach
    void beforeEach() {
        this.simplyCookTester.initialize();
        this.simplyCookTester.openWebsite();
        this.simplyCookTester.navigateLogin();
        this.simplyCookTester.proceedWithoutRegistration();
    }

    //проверка на то если китов в списке меньше 4х то книпка continue не должна появляться
    @Test
    void checkoutWithFourRecipiesAllowed() {
        List<String> kitsToOrder = new ArrayList<>();
        kitsToOrder.add("Goan Fish Curry");
        kitsToOrder.add("Cuban Prawn Pasta");
        kitsToOrder.add("Balinese Chicken");
        kitsToOrder.add("Jambalaya");

        this.simplyCookTester.addKitsToOrder(kitsToOrder);
        assertTrue(this.simplyCookTester.isElementDisplayed(this.simplyCookTester.firstBoxxPath));
        assertTrue(this.simplyCookTester.isElementDisplayed(this.simplyCookTester.checkoutButtonxPath));
    }

    @Test
    void checkoutWithThreeRecipiesForbidden() {
        List<String> kitsToOrder = new ArrayList<>();
        kitsToOrder.add("Goan Fish Curry");
        kitsToOrder.add("Cuban Prawn Pasta");
        kitsToOrder.add("Balinese Chicken");

        this.simplyCookTester.addKitsToOrder(kitsToOrder);

        assertFalse(this.simplyCookTester.isElementDisplayed(this.simplyCookTester.firstBoxxPath));
        assertFalse(this.simplyCookTester.isElementDisplayed(this.simplyCookTester.checkoutButtonxPath));
    }

    @AfterEach
    void afterEach() {
        this.simplyCookTester.destroy();
    }

    @AfterAll
    void afterAll() {
        System.out.println("Close browser");
    }

}
