package com.qa.Util;

import java.time.Duration;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.apache.commons.io.FileUtils;

public class Utils {

    public static boolean isElementPresent(WebDriver driver, By locator, int timeoutInSeconds){
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
            return !elements.isEmpty();
        }
        catch (org.openqa.selenium.TimeoutException e){
            return false;
        }
    }

    public static boolean isElementInViewport(WebDriver driver, WebElement element){
        try {
            //Check if the element is present and displayed
            if(!element.isDisplayed()) return false;

            //Get element's coordinates and size
            int elementTop = element.getLocation().getY();
            int elementBottom = elementTop + element.getSize().getHeight();
            int elementLeft = element.getLocation().getX();
            int elementRight = elementLeft + element.getSize().getWidth();

            //Get viewport dimensions and scroll position via JavaScript
            JavascriptExecutor js = (JavascriptExecutor) driver;
            long windowHeight = (long) js.executeScript("return window.innerHeight");
            long windowWidth = (long) js.executeScript("return window.innerWidth");
            long scrollY = (long) js.executeScript("return window.pageYOffset || document.documentElement.scrollTop");
            long scrollX = (long) js.executeScript("return window.pageXOffset || document.documentElement.scrollLeft");

            boolean isVerticallyInView = (elementTop >= scrollY) && (elementBottom <= (scrollY + windowHeight));
            boolean isHorizontallyInView = (elementLeft >= scrollX) && (elementRight <= (scrollX + windowWidth));
            
            return isVerticallyInView && isHorizontallyInView;
        }
        catch(Exception e) {
            System.out.println("Error checking element visibility in viewport: " + e.getMessage());
            return false;
        }
    }

    public static String takeScreenshot(String testName, WebDriver driver) {
        try {
            File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String screenshotPath = "C:\\Users\\dell\\OneDrive\\Desktop\\Codes\\Selenium_web_proj\\selenium-project\\screenshots\\" + "verifyscroll_" + timestamp + ".png";
            File dest = new File(screenshotPath);
            FileUtils.copyFile(src, dest);
            return screenshotPath; }
        catch(Exception e){
            System.out.println("Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }

}
