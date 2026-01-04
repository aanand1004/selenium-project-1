package com.qa.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private WebDriver driver;
    private WebElement productsButton;


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnProducts() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        productsButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/products']")));
        productsButton.click();
    }
    

}
