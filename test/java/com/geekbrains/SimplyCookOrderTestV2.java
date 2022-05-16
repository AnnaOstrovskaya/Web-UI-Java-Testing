package com.geekbrains;

import com.geekbrains.lesson7.ScreenshotOnFailure;
import com.geekbrains.simplycook.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Story("Checking whether the data entered to the About you & delivery block is valid")
@Nested
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SimplyCookOrderTestV2 {
    WebDriver driver;
    Map<String, String> testValues = new HashMap<String, String>();

    @BeforeAll
    void prepareTestValues() {

        // init driver
        WebDriverManager.chromedriver().setup();
        driver = new EventFiringDecorator(new ScreenshotOnFailure()).decorate(new ChromeDriver());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));

        // init test values
        MockNeat mock = MockNeat.threadLocal();
        testValues.put("randomEmail", mock.emails().get());
        testValues.put("randomName", mock.names().first().get());
        testValues.put("randomLastName", mock.names().last().get());

        PostCodeFinder postCodeFinder = new PostCodeFinder(driver);
        postCodeFinder.open();
        testValues.put("randomPostCode", postCodeFinder.generatePostcode());

        // prepare page
        FirstPage firstPage = new FirstPage(driver);
        firstPage.open();
        firstPage.navigateLoginElement.click();
        new LoginRegisterPage(driver).getStarted.click();
        RecipesPage recipesPage = new RecipesPage(driver);
        List<String> kitsToOrder = new ArrayList<>();
        kitsToOrder.add("Goan Fish Curry");
        kitsToOrder.add("Cuban Prawn Pasta");
        kitsToOrder.add("Balinese Chicken");
        kitsToOrder.add("Jambalaya");

        recipesPage.addKitsToOrder(kitsToOrder);
        recipesPage.checkoutButton.click();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage
                .enterEmail(testValues.get("randomEmail"))
                .enterFirstName(testValues.get("randomName"))
                .enterLastName(testValues.get("randomLastName"))
                .enterPostcode(testValues.get("randomPostCode"))
                .clickFindButton()
                .chooseAddress()
                .selectFirstAddressFromDropDown();

    }

    @Feature("Checking whether the data entered in email filed is valid and present")
    @Test
    void checkEmail() {
        String enteredEmail = testValues.get("randomEmail");
        assertFalse(enteredEmail.isEmpty());
        assertEquals(true, enteredEmail.matches("^(.+)@(\\S+)$"));
    }

    @Feature("Checking whether the data entered in first name filed is valid and present")
    @Test
    void checkFirstName() {
        String enteredFirstName =  testValues.get("randomName");
        assertFalse(enteredFirstName.isEmpty());
        assertEquals(true, enteredFirstName.matches("[a-zA-Z]+"));
    }

    @Feature("Checking whether the data entered in last first name filed is valid and present")
    @Test
    void checkLastName() {
        String enteredLastName = testValues.get("randomLastName");
        assertFalse(enteredLastName.isEmpty());
        assertEquals(true, enteredLastName.matches("[a-zA-Z]+"));
    }

    @AfterAll
    void afterAll() {
       driver.quit();
    }

}
