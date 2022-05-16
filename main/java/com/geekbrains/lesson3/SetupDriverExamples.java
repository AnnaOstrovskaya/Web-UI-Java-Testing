package com.geekbrains.lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;

import static org.openqa.selenium.WindowType.TAB;
import static org.openqa.selenium.WindowType.WINDOW;

public class SetupDriverExamples {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("user-agent=Googlebot/2.1 (+http://www.google.com/bot.html)");
        //chromeOptions.addArguments("--headless");
        WebDriver driver = new ChromeDriver();
        driver.get("https://google.com");
        Thread.sleep(5000);

        //открываем новую вкладку в браузере
        driver.switchTo().newWindow(WINDOW);
        Thread.sleep(5000);

       //переходим в другое окно
        driver.switchTo().newWindow(TAB);
        Thread.sleep(5000);


        driver.quit();

    }

}
