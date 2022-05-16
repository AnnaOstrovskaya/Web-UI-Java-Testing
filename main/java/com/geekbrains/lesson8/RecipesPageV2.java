package com.geekbrains.lesson8;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

//public class LoginRecipesPageV2 {
//
////    public LoginRecipesPageV2(WebDriver driver) {
////        super(driver);
////    }
////
////    @FindBy(xpath = "//a[contains (@href, '/recipes/checkout')]" +
////            "//ancestor::button")
////    public WebElement checkoutButton;
////    @FindAll({@FindBy(xpath = "//a[contains (@href, '/recipes/checkout')]" +
////            "//ancestor::button")})
////    public List<WebElement> allCheckoutButtons;
////
////    @FindBy(xpath ="//div[contains (@class, 'MuiDrawer-paperAnchorRight MuiPaper-elevation16')]")
////    public WebElement firstBoxBlock;
////    @FindAll({@FindBy(xpath ="//div[contains (@class, 'MuiDrawer-paperAnchorRight MuiPaper-elevation16')]")})
////    public List<WebElement> firstBoxBlocks;
////
////
//////    @FindBy(xpath = "//a[contains (@href, '/recipes/checkout')]//ancestor::button")
//////    public WebElement continueButton;
////
////    @Step("Adding kito to oreder")
////    private WebElement getAddKitButton(String kitName) {
////        String addKitButtonXpath = "//div[contains(@class,'card-title') and text() = '" + kitName + "']" +
////                "/ancestor::div[contains(@class,'card-body')]//button";
////        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
////        return this.driver.findElement(By.xpath(addKitButtonXpath));
////    }
////    @Step("Click the Add button to add the kit")
////    public void addKitsToOrder(List<String> kitNames) {
////        for (String kitName: kitNames) {
////            getAddKitButton(kitName).click();
////        }
////    }
//
//}
