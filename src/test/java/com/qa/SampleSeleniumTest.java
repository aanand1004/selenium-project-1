package com.qa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SampleSeleniumTest {
    @Test
    public void testGoogle() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com");
        System.out.println("Page Title: " + driver.getTitle());
        driver.quit();
    }
}