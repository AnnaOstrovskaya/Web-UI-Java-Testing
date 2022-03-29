package com.geekbrains;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import static com.geekbrains.lesson4.TriangleSurfaceArea.*;

public class TriangleSurfaceAreaTests {

    private static Logger logger = LoggerFactory.getLogger("TriangleSurfaceAreaTests");

    @BeforeAll
    static void beforeAll() {
        System.out.println("Метод выполнится 1 раз перед всеми тестами");
        logger.info("info log");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("Метод выполнится 1 раз перед каждым тестом");
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "-2" })
    void isZeroOrNegative(int number){
        Assertions.assertFalse(isNegativeOrZero(number));
    }


    @AfterEach
    void afterEach () {
        System.out.println("Метод выполнится 1 раз после каждого теста");
        //driver.quit();
    }

    @AfterAll
    static void afterAll () {
        System.out.println("Метод выполнится 1 раз после всех тестов");
    }


}
