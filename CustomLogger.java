package com.geekbrains.lesson7;

import io.qameta.allure.Allure;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.WebDriverListener;
import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;

import static org.openqa.selenium.OutputType.BYTES;

public class CustomLogger implements WebDriverListener {

    private static Logger logger = LoggerFactory.getLogger(CustomLogger.class);

    public void beforeFindElement(WebDriver driver, By locator) {
        Allure.step("Serachng element by locator" + locator);
    }

    public void beforeQuit(WebDriver driver) {
        Allure.addAttachment("Screenshot before serching element",
                new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(BYTES)));
    }

}
