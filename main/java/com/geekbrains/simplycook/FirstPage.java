package com.geekbrains.simplycook;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FirstPage extends BasePage {

    @FindBy(xpath = "//a[@href = '/login-register']")
    public WebElement navigateLoginElement;

    public FirstPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("https://www.simplycook.com/");
    }


    @Step("Clicking human looking element on the right top corner of the screen that takes you to the login-register page")
    public BasePage navigateToLogin() {
        navigateLoginElement.click();
        return this;
    }
}
