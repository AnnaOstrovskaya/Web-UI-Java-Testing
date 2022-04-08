package com.geekbrains.Simplycookpageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginRegisterPage extends BasePage{

    public LoginRegisterPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@href = '/recipes']")
    public WebElement getStarted;

}
