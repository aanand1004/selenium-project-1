package com.qa.pages;

import java.time.Duration;

import javax.sql.rowset.WebRowSet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductDetailsPage {
    private WebDriver driver;
    WebElement Price;

    public ProductDetailsPage(WebDriver driver){
        this.driver = driver;
    }

    public String returnPrice(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Price = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Rs. 500']")));
        return Price.getText();
    }

}
