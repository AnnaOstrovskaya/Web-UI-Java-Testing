package com.geekbrains.simplycook;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginRegisterPage extends BasePage{

    public LoginRegisterPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@href = '/recipes']")
    public WebElement getStarted;

//    @Step("Clicking Get started button")
//    public BasePage navigateToLogin() {
//        getStarted.click();
//        return this;
//    }

}
