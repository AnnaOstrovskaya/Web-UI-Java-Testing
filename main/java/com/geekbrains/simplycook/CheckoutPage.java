package com.geekbrains.simplycook;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "emailAddress")
    public WebElement emailAddressField;

    @FindBy(id = "firstName")
    public WebElement firstNameField;

    @FindBy(id = "lastName")
    public WebElement lastNameField;

    @FindBy(id = "searchCode")
    public WebElement postCodeField;

    @FindBy(xpath = "//button[. = \"FIND\"]")
    public WebElement findAddressButton;

    @FindBy(xpath = "//div[@class = 'MuiSelect-root n136tiEC2 MuiSelect-select " +
            "MuiSelect-selectMenu MuiSelect-outlined MuiInputBase-input MuiOutlinedInput-input']")
    public WebElement pickYourAddressField;

    @FindBy(xpath = "//li[@data-testid='select-option' and  @data-value='0' ]")
    public WebElement firstAddressListEntry;

    @Step("Entering email")
    public CheckoutPage enterEmail (String email) {
        emailAddressField.sendKeys(email);
        return this;
    }
    @Step("Entering first name")
    public CheckoutPage enterFirstName (String firstName) {
        firstNameField.sendKeys(firstName);
        return this;
    }
    @Step("Entering Last name")
    public CheckoutPage enterLastName (String lastName) {
        lastNameField.sendKeys(lastName);
        return this;
    }
    @Step("Entering Postcode that has been saved from the PostcodeFnder")
    public CheckoutPage enterPostcode (String postCode) {
        postCodeField.sendKeys(postCode);
        return this;
    }
    @Step("Clicking Find button")
    public  CheckoutPage  clickFindButton(){
        findAddressButton.click();
        return this;
    }
    @Step("Clicking at Pick your address field")
    public CheckoutPage chooseAddress(){
        pickYourAddressField.click();
        return this;
    }
    @Step("Selecting first address from the dropdown menu")
    public CheckoutPage selectFirstAddressFromDropDown() {
        firstAddressListEntry.click();
        return this;
    }

}






