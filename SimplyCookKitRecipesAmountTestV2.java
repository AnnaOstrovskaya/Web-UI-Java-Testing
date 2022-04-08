package com.geekbrains;

import com.geekbrains.Simplycookpageobject.FirstPage;
import com.geekbrains.Simplycookpageobject.LoginRegisterPage;
import com.geekbrains.Simplycookpageobject.RecipesPage;
import com.geekbrains.Simplycookpageobject.YourFirstBoxBlock;
import com.geekbrains.lesson7.CustomLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Story("Cheking whether contunue button and First box block exist")
@Nested
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SimplyCookKitRecipesAmountTestV2 {
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;

    @BeforeAll
    static void registerDriver() {
        System.out.println("Initialising SimplyCookTests.");
    }

    @BeforeEach
    void initDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new EventFiringDecorator(new CustomLogger()).decorate(new ChromeDriver());
        driver.get("https://www.simplycook.com/");
        FirstPage firstPage = new FirstPage(driver);
        firstPage.navigateLogin.click();


        new LoginRegisterPage(driver).getStarted.click();
    }

    @Test
    @Feature("Cheking that the First Box block and Contunue button  apper after we add 4 kits to the box")
    void checkoutWithFourRecipiesAllowed() {

        RecipesPage recipesPage = new RecipesPage(driver);
        List<String> kitsToOrder = new ArrayList<>();
        kitsToOrder.add("Goan Fish Curry");
        kitsToOrder.add("Cuban Prawn Pasta");
        kitsToOrder.add("Balinese Chicken");
        kitsToOrder.add("Jambalaya");

        recipesPage.addKitsToOrder(kitsToOrder);

        assertFalse(recipesPage.firstBoxBlocks.isEmpty());
        assertFalse(recipesPage.allCheckoutButtons.isEmpty());

        recipesPage.checkoutButton.click();

    }

    @Test
    @Feature("Cheking that the First Box block and Contunue button on it do not apper after we add 3 kits to the box")
    void checkoutWithThreeRecipiesNotAllowed() {

        RecipesPage recipesPage = new RecipesPage(driver);
        List<String> kitsToOrder = new ArrayList<>();
        kitsToOrder.add("Goan Fish Curry");
        kitsToOrder.add("Cuban Prawn Pasta");
        kitsToOrder.add("Balinese Chicken");

        recipesPage.addKitsToOrder(kitsToOrder);

        assertTrue(recipesPage.firstBoxBlocks.isEmpty());
        assertTrue(recipesPage.allCheckoutButtons.isEmpty());

    }


    @AfterAll
    void afterAll() {
        WebDriverManager.chromedriver().quit();
    }

}

