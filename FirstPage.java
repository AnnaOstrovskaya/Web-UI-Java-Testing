package com.geekbrains.Simplycookpageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirstPage extends BasePage {

    @FindBy(xpath = "//a[@href = '/login-register']")
    public WebElement navigateLogin;

    public FirstPage(WebDriver driver) {
        super(driver);
    }
}
