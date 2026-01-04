package com.qa.tests;

import java.time.Duration;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;

public class BaseTest {
    protected WebDriver driver;
    ExtentSparkReporter extentSparkReporter;
    ExtentReports extentReports;
    ExtentTest extentTest;

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        System.out.println("Webdriver is initialized in BaseTest");
        extentSparkReporter = new ExtentSparkReporter("C:\\Users\\aanan\\OneDrive\\Desktop\\All\\Codes\\Selenium_web_proj\\selenium-project\\reports\\Extenreport1.html");
        extentSparkReporter.config().setTimeStampFormat("yyyy-MM-dd HH:mm:ss");
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        
        driver.manage().window().maximize();
        driver.get("https://www.automationexercise.com/");

    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Webdriver is closed in BaseTest");
            extentReports.flush();
        }
    }

}
