package com.geekbrains.Simplycookpageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FirstPage extends BasePage {

    @FindBy(xpath = "//a[@href = '/login-register']")
    public WebElement navigateLogin;

    public FirstPage(WebDriver driver) {
        super(driver);
    }
}
