package com.geekbrains.lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class DiaryTest {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://diary.ru/");

        //этод метод позволяет избавиться от проблемы неподгрузки элементов
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        //авторизация с помощью логина и пароля

        //нажимаем на кнопку войти
        WebElement loginButton = driver.findElement(By.xpath("//a[@href = '/login-register']"));
        loginButton.click();
        /*
        //другой метод
        driver.findElement(By.xpath("//a[.='Вход']")).click();

        //вводим логин и пароль
        driver.findElement(By.id("loginform-username")).sendKeys("Anna_Ostro");
        driver.findElement(By.id("loginform-password")).sendKeys("3qIImYZYbX");

        //отключаем капчу
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='reCAPTCHA']"))); //переключаемся в фрэйм капчи
        driver.findElement(By.xpath("//div[@class='recaptcha-checkbox-border']")).click();

        //возвращаемся в общий фрэйм
        driver.switchTo().parentFrame();

        //нажимаем кнопку войти
        driver.findElement(By.id("login_btn")).click();

        Thread.sleep(5000);

        */

        //авторизуемся с помощью куки
        Cookie authCookie = new Cookie("_identity_", "8205905595120812bfa04a3a041007d3763fe6f94241fa6b4fdef" +
                "e00b36248bda%3A2%3A%7Bi%3A0%3Bs%3A10%3A%22_identity_%22%3Bi%3A1%3Bs%3A52%3A%22%5B3564888%2C%22ZEV3e_" +
                "xPED3ODKc_thT_9jq2bLlUQIzx%22%2C2592000%5D%22%3B%7D");

        driver.manage().addCookie(authCookie);
        driver.navigate().refresh();

        //создаем новый пост
        driver.findElement(By.xpath("//a[@title='Новая запись']")).click();

        //даем посту название
        String postName = "Post" + new Random().nextInt(1000);

        driver.findElement(By.id("postTitle")).sendKeys(postName);
        driver.switchTo().frame(driver.findElement(By.id("message_ifr")));

        //вводим текст в пост
        driver.findElement(By.id("tinymce")).sendKeys("Our post text");
        driver.switchTo().parentFrame();

        //сохраняме пост
        driver.findElement(By.id("rewrite")).click();

       // driver.findElement(By.xpath(String.format("//a[.='%s']", postName))).click();

        List<WebElement> posts = driver.findElements(By.xpath("//a[@class='title']"));
        posts.stream().filter(p -> p.getText().contains(postName)).findFirst().get().click();
        Thread.sleep(5000);

        driver.quit();

    }
}
