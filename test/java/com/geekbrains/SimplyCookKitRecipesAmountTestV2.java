package com.geekbrains;

import com.geekbrains.simplycook.FirstPage;
import com.geekbrains.simplycook.LoginRegisterPage;
import com.geekbrains.simplycook.RecipesPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Story("Checking whether continue button and First box block exist")
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
        //driver = new EventFiringDecorator(new ScreenshotOnFailure()).decorate(new ChromeDriver());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        driver.get("https://www.simplycook.com/");
        FirstPage firstPage = new FirstPage(driver);
        firstPage.navigateLoginElement.click();

        new LoginRegisterPage(driver).getStarted.click();
    }

    @Test
    @Feature("Checking that the First Box block and Continue button appear after we add 4 kits to the box")
    void checkoutWithFourRecipesAllowed() {

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
    @Feature("Checking that the First Box block and Continue button on it do not appear after we add 3 kits to the box")
    void checkoutWithThreeRecipesNotAllowed() {

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
        driver.quit();
    }

}

