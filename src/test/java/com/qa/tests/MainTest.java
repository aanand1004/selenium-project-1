package com.qa.tests;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.qa.pages.HomePage;
import com.qa.pages.MainPage;
import com.qa.pages.ProductDetailsPage;
import com.qa.pages.ProductsPage;
import com.qa.pages.SigninPage;
import com.qa.pages.ViewCartPage;

import io.github.bonigarcia.wdm.WebDriverManager;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.qa.Util.Utils;

public class MainTest extends BaseTest {
    private HomePage homePage;
    private SigninPage signinPage;
    private ProductsPage productsPage;
    private ViewCartPage viewCartPage;
    


    @Test(priority = 1)
    public void verifysignin1() {
        try {
            extentTest = extentReports.createTest("Test 1", "This test verifies the signin functionality");
            homePage = new HomePage(driver);
            signinPage = new SigninPage(driver);
            extentTest.log(Status.INFO, "About to click login in Home page");
            homePage.clickLogin();
            Assert.assertEquals(driver.getCurrentUrl(), "https://www.automationexercise.com/login", "Login Failed");
            extentTest.log(Status.INFO, "Clicked on login and entered Home Page");
            signinPage.signin("aanandfun@gmail.com", "vitgc");
            Assert.assertEquals(Utils.isElementPresent(driver, By.xpath("//p[normalize-space()='Your email or password is incorrect!']"), 30), true);
            extentTest.log(Status.PASS, "Test 1 succeeded"); }
        catch(AssertionError e) {
            extentTest.log(Status.FAIL, "Test 1 Failed with error: " + e.getMessage());
            throw e;
        }
            
    }

    @Test(priority = 2)
    public void verifysignin2() {
        signinPage.clearemailField();
        signinPage.clearpwdField();
        signinPage.signin("aanandfun@gmail.com", "visitgc");
        Assert.assertEquals(Utils.isElementPresent(driver, By.xpath("//li[10]//a[1]"), 30), true);
    }


    @Test(priority = 3, dependsOnMethods = {"verifysignin2"} )
    public void verifyProducts() {
        try {
            MainPage mainPage = new MainPage(driver);
            mainPage.clickOnProducts();
            Assert.assertTrue(driver.getCurrentUrl().contains("products"));
            Thread.sleep(Duration.ofSeconds(3));
            productsPage = new ProductsPage(driver);
            Assert.assertEquals(productsPage.getAllProducts().size(), 34, "Expected number of products not present");
            productsPage.getProductByIndex(0).clickViewProduct();
            ProductDetailsPage productDetailsPage = new ProductDetailsPage(driver);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[normalize-space()='Blue Top']")));
            //Assert.assertEquals(driver.findElement(By.xpath("//h2[normalize-space()='Blue Top']")).getText(), "Blue Top");
            Assert.assertEquals(productDetailsPage.returnPrice(), "Rs. 500");
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }    

    }

    @Test(priority = 4, dependsOnMethods = {"verifysignin2"})
    public void addToCart() {
        try {
            driver.navigate().back();
            productsPage.getProductByIndex(0).clickAddToCart();
            productsPage.clickContinueShopping();
            Thread.sleep(Duration.ofSeconds(2));
            productsPage.getProductByIndex(0).clickAddToCart();
            productsPage.clickContinueShopping();
            productsPage.getProductByIndex(1).clickAddToCart();
            productsPage.clickViewCart();
            Thread.sleep(Duration.ofSeconds(2));
            viewCartPage = new ViewCartPage(driver);
            //Assert.assertEquals(viewCartPage.getCartItemByIndex(0).getQuantity(), "2");
            Assert.assertEquals(viewCartPage.getCartItemByIndex(1).getItemName(), "Men Tshirt");
            String priceString = viewCartPage.getCartItemByIndex(0).getPrice().substring(4);
            int price = Integer.parseInt(priceString);
            String qtyString = viewCartPage.getCartItemByIndex(0).getQuantity();
            int qty = Integer.parseInt(qtyString);
            String totPriceString = viewCartPage.getCartItemByIndex(0).getTotalPrice().substring(4);
            int totPrice = Integer.parseInt(totPriceString);
            Assert.assertEquals(price * qty, totPrice);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //@Test(priority = 5, dependsOnMethods = {"verifysignin2"})


}