package com.geekbrains.lesson8;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;

public class FirstPageV2 {

    private SelenideElement navigateLogin = $(By.xpath("//a[@href = '/login-register']"));

}

