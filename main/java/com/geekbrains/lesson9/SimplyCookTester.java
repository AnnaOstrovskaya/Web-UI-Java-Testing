package com.geekbrains.lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import net.andreinc.mockneat.MockNeat;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.openqa.selenium.WindowType.TAB;


public class SimplyCookTester {

    public ChromeDriver seleniumDriver;
    public String webAddress = "https://www.simplycook.com/";

    public void initializeSelenium() {
        WebDriverManager.chromedriver().setup();
        this.seleniumDriver = new ChromeDriver();
        this.seleniumDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
    }

    public void placeOrder(List<String> kitsToOrder) {
        this.seleniumDriver.get(this.webAddress);


        // 1 нажать на человечка
        WebElement loginButton = this.seleniumDriver.findElement(By.xpath("//a[@href = '/login-register']"));
        loginButton.click();

        // 2 нажать на get started
        this.seleniumDriver.findElement(By.xpath("//a[@href = '/recipes']")).click();

        // 3 добавить 4 рецепта в корзину
        for (String kitName : kitsToOrder) {
            String xpath = "//div[contains(@class,'card-title') and text() = '" + kitName + "']/ancestor::div[contains(@class,'card-body')]//button";
            WebDriverWait wait = new WebDriverWait(this.seleniumDriver, Duration.ofSeconds(10));
            WebElement kitElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
            kitElement.click();
        }

        // 4 нажать на continue в новом окне
        this.seleniumDriver.findElement(By.xpath("//a[contains (@href, '/recipes/checkout')]//ancestor::button")).click();

        // 5 ввести имэйл, имя, фамилию
        MockNeat mock = MockNeat.threadLocal();
        String randomEmail = mock.emails().get();
        String randomName =  mock.names().first().get();
        String randomLastName = mock.names().last().get();

        this.seleniumDriver.findElement(By.id("emailAddress")).sendKeys(randomEmail);
        this.seleniumDriver.findElement(By.id("firstName")).sendKeys(randomName);
        this.seleniumDriver.findElement(By.id("lastName")).sendKeys(randomLastName);

        // 8 ввести почтовый код
        this.seleniumDriver.switchTo().newWindow(TAB);
        this.seleniumDriver.get("https://www.doogal.co.uk/PostcodeGenerator.php");

        this.seleniumDriver.findElement(By.xpath("//div[@class = 'qc-cmp2-summary-buttons']//button[. = \"AGREE\"]")).click();

        this.seleniumDriver.findElement(By.id("createPostcodeButton")).click();

        String postCode = this.seleniumDriver.findElement(By.id("postcodeDetail")).getText();

        List<String> tabs = new ArrayList<>(this.seleniumDriver.getWindowHandles());
        this.seleniumDriver.switchTo().window(tabs.get(0));

        this.seleniumDriver.findElement(By.id("searchCode")).sendKeys(postCode);

        // 9 нажать  на кнопку find
        this.seleniumDriver.findElement(By.xpath("//button[. = \"FIND\"]")).click();

        this.seleniumDriver.findElement(By.xpath("//div[@class = 'MuiSelect-root n136tiEC2 MuiSelect-select " +
                "MuiSelect-selectMenu MuiSelect-outlined MuiInputBase-input MuiOutlinedInput-input']")).click();

        //10 выбрать адрес
        List<WebElement> options = this.seleniumDriver.findElements(By.xpath("//div[@class = 'MuiPaper-root MuiMenu-paper MuiPopover-paper " +
                "MuiPaper-elevation8 MuiPaper-rounded']"));
        Random rand = new Random();
        int list= rand.nextInt(options.size());
        options.get(list).click();
        this.seleniumDriver.switchTo().window(tabs.get(1)).close();
        this.seleniumDriver.switchTo().window(tabs.get(0));
    }

    public String getCheckoutEmail() {
        String enteredEmail = this.seleniumDriver.findElement(By.id("emailAddress")).getAttribute("value");
        return enteredEmail;
    }

    public static boolean isfirstNameValid(String nameToCheck) {
        return (nameToCheck == null) || (nameToCheck.isEmpty());
    }

    public static boolean islastNameValid(String lastNameToCheck) {
        return (lastNameToCheck == null) || (lastNameToCheck.isEmpty());
    }
}