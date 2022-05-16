package com.geekbrains;

import com.geekbrains.lesson9.SimplyCookTesterComplex;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import java.util.List;

@Nested
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SimplyCookOrderTests {

    private static Logger logger = LoggerFactory.getLogger("SimplyCookTests");
    public SimplyCookTesterComplex simplyCookTester;

    @BeforeAll
    void beforeAll() {
        System.out.println("Initialising SimplyCookTester..");
        this.simplyCookTester = new SimplyCookTesterComplex();
        this.simplyCookTester.initialize();

        List<String> kitsToOrder = new ArrayList<>();
        kitsToOrder.add("Goan Fish Curry");
        kitsToOrder.add("Cuban Prawn Pasta");
        kitsToOrder.add("Balinese Chicken");
        kitsToOrder.add("Jambalaya");

        this.simplyCookTester.openWebsite();
        this.simplyCookTester.navigateLogin();
        this.simplyCookTester.proceedWithoutRegistration();
        this.simplyCookTester.addKitsToOrder(kitsToOrder);
        this.simplyCookTester.checkoutOrderFillForm();
    }

    @BeforeEach
    void beforeEach() {
    }

    // проверить введен ли имэйл и формат
    @Test
    void checkEmail() {
        String enteredEmail = this.simplyCookTester.getFormInputValue("emailAddress");
        assertFalse(enteredEmail.isEmpty());
        assertEquals(true, enteredEmail.matches("^(.+)@(\\S+)$"));
    }

    //проверить введено ли имя и формат
    @Test
    void checkFirstName() {
        String enteredFirstName = this.simplyCookTester.getFormInputValue("firstName");
        assertFalse(enteredFirstName.isEmpty());
        assertEquals(true, enteredFirstName.matches("[a-zA-Z]+"));
    }

    //проверить введена ли фамилия и формат
    @Test
    void checkLastName() {
        String enteredLastName = this.simplyCookTester.getFormInputValue("lastName");
        assertEquals(true, enteredLastName.matches("[a-zA-Z]+"));
    }

    @AfterEach
    void afterEach() {
        System.out.println("Метод выполнится 1 раз после каждого теста");
    }

    @AfterAll
    void afterAll() {
        System.out.println("Close browser");
        this.simplyCookTester.destroy();
    }

}
