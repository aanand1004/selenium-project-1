package com.qa.tests;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.qa.Util.Utils;
import com.qa.pages.HomePage;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;

public class Test2 extends BaseTest {
    private String screenShotPath;


    @Test(priority = 1)
    public void verifyscroll() throws Exception{
        try {
            extentTest = extentReports.createTest("Test 1", "This test verifies scrolling functionality");
            HomePage homePage = new HomePage(driver);
            
            // Wait up to 20 seconds for the page to fully load
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(driver1 -> // driver1 is a temporary name for the WebDriver
                ((JavascriptExecutor) driver1) // Use driver1 to run JavaScript
                .executeScript("return document.readyState").equals("complete"));

            screenShotPath = Utils.takeScreenshot("verifyScroll1", driver);
            extentTest.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath).build());
            Assert.assertEquals(Utils.isElementInViewport(driver, homePage.getSubscriptionElement()), false);

            
            //Scroll to the bottom of the page
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

            screenShotPath = Utils.takeScreenshot("verifyScroll1", driver);
            extentTest.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath).build());
            Thread.sleep(Duration.ofSeconds(7));
            Assert.assertEquals(Utils.isElementInViewport(driver, homePage.getSubscriptionElement()), true);

            //Go to the top of the page and verify if the text is visible and in viewports
            homePage.clickOngoToTopButton();
            wait.until(ExpectedConditions.visibilityOf(homePage.gettextAtTopElement()));
            screenShotPath = Utils.takeScreenshot("verifyScroll1", driver);
            extentTest.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath).build());
            System.out.println(homePage.gettextAtTopElement().getText());
            Assert.assertEquals(Utils.isElementInViewport(driver, homePage.gettextAtTopElement()), true);
            extentTest.log(Status.PASS, "Test 1 succeeded");

        } catch(Exception e) {
            System.out.println("Exception message is: " + e);
            extentTest.log(Status.FAIL, "Test failed with error: " + e);
            throw e;
        }

    }

}
