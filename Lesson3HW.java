package com.geekbrains.lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import net.andreinc.mockneat.MockNeat;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.openqa.selenium.WindowType.TAB;


public class Lesson3HW {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.simplycook.com/");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        // 1 нажать на человечка
        WebElement loginButton = driver.findElement(By.xpath("//a[@href = '/login-register']"));
        loginButton.click();

        // 2 нажать на get started
        driver.findElement(By.xpath("//a[@href = '/recipes']")).click();

        // 3 добавить 4 рецепта в корзину
        driver.findElement(By.xpath("//div[contains(@class,'card-title') and text() = 'Goan Fish Curry']" +
                "/ancestor::div[contains(@class,'card-body')]//button")).click();

        driver.findElement(By.xpath("//div[contains(@class,'card-title') and text() = 'Cuban Prawn Pasta']" +
                "/ancestor::div[contains(@class,'card-body')]//button")).click();

        driver.findElement(By.xpath("//div[contains(@class,'card-title') and text() = 'Balinese Chicken']" +
                "/ancestor::div[contains(@class,'card-body')]//button")).click();

        driver.findElement(By.xpath("//div[contains(@class,'card-title') and text() = 'Jambalaya']" +
                "/ancestor::div[contains(@class,'card-body')]//button")).click();

        // 4 нажать на continue в новом окне
        driver.findElement(By.xpath("//a[contains (@href, '/recipes/checkout')]//ancestor::button")).click();

        // 5 ввести имэйл
        MockNeat mock = MockNeat.threadLocal();
        String randomEmail = mock.emails().get();
        String randomName =  mock.names().first().get();
        String randomLastName = mock.names().last().get();

        driver.findElement(By.id("emailAddress")).sendKeys(randomEmail);
        driver.findElement(By.id("firstName")).sendKeys(randomName);
        driver.findElement(By.id("lastName")).sendKeys(randomLastName);

        // 8 ввести почтовый код
        driver.switchTo().newWindow(TAB);
        driver.get("https://www.doogal.co.uk/PostcodeGenerator.php");

        driver.findElement(By.xpath("//div[@class = 'qc-cmp2-summary-buttons']//button[. = \"AGREE\"]")).click();

        driver.findElement(By.id("createPostcodeButton")).click();

        String postCode = driver.findElement(By.id("postcodeDetail")).getText();

        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));

        driver.findElement(By.id("searchCode")).sendKeys(postCode);

        // 9 нажать  на кнопку find
        driver.findElement(By.xpath("//button[. = \"FIND\"]")).click();

        driver.findElement(By.xpath("//div[@class = 'MuiSelect-root n136tiEC2 MuiSelect-select " +
                "MuiSelect-selectMenu MuiSelect-outlined MuiInputBase-input MuiOutlinedInput-input']")).click();

        //10выбрать адрес
       List<WebElement> options = driver.findElements(By.xpath("//div[@class = 'MuiPaper-root MuiMenu-paper MuiPopover-paper " +
               "MuiPaper-elevation8 MuiPaper-rounded']"));
       Random rand = new Random();
       int list= rand.nextInt(options.size());
       options.get(list).click();

    }
}