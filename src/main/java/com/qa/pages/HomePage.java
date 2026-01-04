package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebElement signin;
    private By subscription = By.cssSelector("div[class='single-widget'] h2");
    private By goToTopButton = By.cssSelector(".fa.fa-angle-up");
    private By textAtTop = By.cssSelector("div[class='item active'] h2");
    
    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public void clickLogin(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        signin = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Signup / Login']")));
        signin.click();
    }

    public String getSubscriptionText(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement subscriptionelement = wait.until(ExpectedConditions.visibilityOfElementLocated(subscription));
        return subscriptionelement.getText();
    }

    public WebElement getSubscriptionElement(){
        return driver.findElement(subscription);
    }

    public void clickOngoToTopButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(goToTopButton)).click();
    }

    public WebElement gettextAtTopElement(){
        return driver.findElement(textAtTop);
    }
}