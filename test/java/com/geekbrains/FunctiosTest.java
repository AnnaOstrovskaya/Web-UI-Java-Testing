package com.geekbrains;

import com.geekbrains.lesson4.Cat;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.commons.logging.LoggerFactory;

import java.util.logging.Logger;
import java.util.stream.Stream;

import static com.geekbrains.lesson4.Functions.isPalindrome;
import static com.geekbrains.lesson4.Functions.isPrime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class FunctiosTest {

    private static Logger logger = (Logger) LoggerFactory.getLogger(FunctiosTest.class);

    @BeforeAll
    static void beforeAll() {
        System.out.println("Метод выполнится 1 раз перед всеми тестами");
        logger.info("info log");
        logger.warning("warn log");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("Метод выполнится 1 раз перед каждым тестом");
    }

    @Test
        //@DisplayName() можно использовать чтобы объяснить что тестиреут наш метод
    void givenPrimeNumberWhenCallPrimeThenTrue() {
        boolean result = isPrime(7);//вызываем метод Prime из Functions, даем ему число и сохраняем результат в переменную типа boolean
        Assertions.assertTrue(result);
    }

    //@Test
    //void isPalindromPositiveTest1() {
    //    Assertions.assertTrue(isPalindrome("123321"));
    //}

    //@Test
    //void isPalindromPositiveTest2() {
    //    Assertions.assertTrue(isPalindrome("1234321"));
    //}

    @ParameterizedTest //испольовать кога тест одинковый а данные для проверки разные
    @ValueSource (strings = {"1234321", "123321" })
    void isPalindromePositiveTest(String word){
        Assertions.assertTrue(isPalindrome (word));
    }

    @Test
    void isNotPalindrome() {
        assertFalse(isPalindrome("12"));
    }

    @ParameterizedTest//использовать чтобы сразу запустить 2 проверки на true и false
    @CsvSource({"true, 123321", "false, 123"})
    void commonPalinromeTest(boolean expectedResult, String word) {
        assertEquals(expectedResult, isPalindrome (word));
    }

    @ParameterizedTest
    @MethodSource ("catAndAgeDataProvider")
    void catTest (Cat cat, Integer age) {
        assertEquals(age, cat.getAge());
    }

    private static Stream<Arguments> catAndAgeDataProvider() {
        return Stream.of(
                Arguments.of(new Cat ("Barsik", 10), 10),
                Arguments.of(new Cat ("Mursik", 10), 11)
        );
    }


    @AfterEach
    void afterEach () {
        System.out.println("Метод выполнится 1 раз после каждого теста");

    }

    @AfterAll
    static void afterAll () {
        System.out.println("Метод выполнится 1 раз после всех тестов");
    }

}
