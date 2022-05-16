package com.geekbrains.lesson9;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.andreinc.mockneat.MockNeat;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.openqa.selenium.WindowType.TAB;

public class SimplyCookTesterComplex {

    private ChromeDriver seleniumDriver;
    private String webAddress = "https://www.simplycook.com/";

    public String firstBoxxPath = "//div[@class = \"MuiPaper-root MuiDrawer-paper flex-column" +
            " F2Ow1x9fq MuiDrawer-paperAnchorRight MuiPaper-elevation16\"]";
    public String checkoutButtonxPath = "//a[contains (@href, '/recipes/checkout')]" +
            "//ancestor::button";

    public void initialize() {
        System.setProperty("webdriver.chrome.silentOutput", "true");
        WebDriverManager.chromedriver().setup();
        this.seleniumDriver = new ChromeDriver();
        this.seleniumDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
    }

    public void destroy() {
        this.seleniumDriver.quit();
    }

    public void openWebsite() {
        this.seleniumDriver.get(this.webAddress);
    }

    // 1 нажать на человечка
    public void navigateLogin() {
        WebElement loginButton = this.seleniumDriver.findElement(By.xpath("//a[@href = '/login-register']"));
        loginButton.click();
    }

    // 2 нажать на get started
    public void proceedWithoutRegistration() {
        this.seleniumDriver.findElement(By.xpath("//a[@href = '/recipes']")).click();
    }

    // 3 добавить 4 рецепта в корзину
    public void addKitsToOrder(List<String> kitsToOrder) {
        for (String kitName : kitsToOrder) {
            String xpath = "//div[contains(@class,'card-title') and text() = '" + kitName + "']" +
                    "/ancestor::div[contains(@class,'card-body')]//button";
            WebDriverWait wait = new WebDriverWait(this.seleniumDriver, Duration.ofSeconds(10));
            WebElement kitElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
            kitElement.click();
        }
    }

    // 4 нажать на continue в новом окне
    public void pressCheckoutContinueButton() {
        this.seleniumDriver.findElement(By.xpath("//a[contains (@href, '/recipes/checkout')]" +
                "//ancestor::button")).click();
    }

    public void checkoutOrderFillForm() {

        this.pressCheckoutContinueButton();

        // 5 ввести имэйл, имя, фамилию
        MockNeat mock = MockNeat.threadLocal();
        String randomEmail = mock.emails().get();
        String randomName = mock.names().first().get();
        String randomLastName = mock.names().last().get();

        this.seleniumDriver.findElement(By.id("emailAddress")).sendKeys(randomEmail);
        this.seleniumDriver.findElement(By.id("firstName")).sendKeys(randomName);
        this.seleniumDriver.findElement(By.id("lastName")).sendKeys(randomLastName);

        // 8 ввести почтовый код
        this.seleniumDriver.switchTo().newWindow(TAB);
        this.seleniumDriver.get("https://www.doogal.co.uk/PostcodeGenerator.php");

        this.seleniumDriver.findElement(By.xpath("//div[@class = 'qc-cmp2-summary-buttons']" +
                "//button[. = \"AGREE\"]")).click();

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
        List<WebElement> options = this.seleniumDriver.findElements(By.xpath("//div[@class = " +
                "'MuiPaper-root MuiMenu-paper MuiPopover-paper " +
                "MuiPaper-elevation8 MuiPaper-rounded']"));
        Random rand = new Random();
        int list = rand.nextInt(options.size());
        options.get(list).click();
        //this.seleniumDriver.switchTo().window(tabs.get(1)).close();
        this.seleniumDriver.switchTo().window(tabs.get(0));
    }

    public String getFormInputValue(String inputId) {
        return this.seleniumDriver.findElement(By.id(inputId)).getAttribute("value");
    }

    public boolean isElementDisplayed(String xpath) {
        return !this.seleniumDriver.findElements(By.xpath(xpath)).isEmpty();
    }

    public void removeKitFromOrder(String kitName) {
        String xpath = "//button[@aria-label = 'Remove " + kitName + " from box']";
        this.seleniumDriver.findElement(By.xpath(xpath)).click();
    }

/*
        public boolean removeAllKitsFromOrder() {
            String xpath = "//button[contains(@aria-label, 'Remove ' )][contains(@class,'MuiButtonBase-root')]";
            List<WebElement> removeButtons =  this.seleniumDriver.findElements(By.xpath(xpath));
            for (WebElement removeButton : removeButtons) {
                WebDriverWait wait = new WebDriverWait(this.seleniumDriver, Duration.ofSeconds(10));
                WebElement kitElement = wait.until(ExpectedConditions.elementToBeClickable(removeButton));
               kitElement.click();
            }
*/

    public void removeAllKitsFromOrder() {
        String removeButtonXpath = "//button[contains(@aria-label, 'Remove ' )][contains(@class,'MuiButtonBase-root')]";
        List<WebElement> removeButtons = this.seleniumDriver.findElements(By.xpath(removeButtonXpath));
        Integer numberOfOrderedKits = removeButtons.size();
        for (int i = 0; i < numberOfOrderedKits; i++) {
            WebDriverWait wait = new WebDriverWait(this.seleniumDriver, Duration.ofSeconds(10));
            WebElement buttonElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(removeButtonXpath)));
            buttonElement.click();
        }
    }



}



