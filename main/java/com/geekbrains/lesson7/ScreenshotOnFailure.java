package com.geekbrains.lesson7;

import io.qameta.allure.Attachment;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.OutputType.BYTES;


public class ScreenshotOnFailure extends TestWatcher {
    WebDriver driver;

    @Rule
    public TestWatcher screenshotOnFailure = new TestWatcher() {


        @Override
        protected void failed(Throwable e, Description description) {
            makeScreenshotOnFailure();
        }

        @Attachment("Screenshot on failure")
        public byte[] makeScreenshotOnFailure() {
            return (((TakesScreenshot) driver).getScreenshotAs(BYTES));
        }
    };


}
