package com.geekbrains.simplycook;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class PostCodeFinder extends BasePage {

    @FindBy(xpath = "//div[@class = 'qc-cmp2-summary-buttons']//button[. = \"AGREE\"]")
    public WebElement agreeButton;

    @FindBy(id = "createPostcodeButton")
    public WebElement createPostCodeButton;

    @FindBy(id = "postcodeDetail")
    public WebElement postcodeDetail;

    public PostCodeFinder(WebDriver driver) {
        super(driver);
    }

    @Step("Generating postcode and getting generated postcode")
    public String generatePostcode(){
        try {
            agreeButton.click();
        }
        catch(org.openqa.selenium.StaleElementReferenceException ex)
        {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(agreeButton)).click();
        }

        createPostCodeButton.click();
        String postCode = postcodeDetail.getText();
        return postCode;
    }

    public BasePage open(){
        driver.get("https://www.doogal.co.uk/PostcodeGenerator.php");
        return this;
    }

}
