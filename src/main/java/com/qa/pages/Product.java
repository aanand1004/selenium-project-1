package com.qa.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Product {
    private WebDriver driver;
    private WebElement productContainer;
    private WebDriverWait wait;

    // Locators relative to the product container
    private By viewProductButton = By.cssSelector("a[href^='/product_details/']"); // Matches "View Product" link by href pattern
    private By addToCartButton = By.cssSelector(".add-to-cart"); // Matches "Add to cart" button
    private By productName = By.cssSelector(".productinfo p"); // Product name <p> tag
    private By productPrice = By.cssSelector(".productinfo h2"); // Product price <h2> tag

    public Product(WebDriver driver, WebElement productContainer) {
        this.driver = driver;
        this.productContainer = productContainer;
    }

    // Get product name
    public String getName() {
        return productContainer.findElement(productName).getText();
    }

    // Get product price
    public String getPrice() {
        return productContainer.findElement(productPrice).getText();
    }

    // Click "View Product" (may require hovering if overlay is hidden)
    public void clickViewProduct() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(viewProductButton));
        productContainer.findElement(viewProductButton).click();
    }

    // Click "Add to cart" (may require hovering if overlay is hidden)
    public void clickAddToCart() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        productContainer.findElement(addToCartButton).click();
    }

    // Additional method if needed, e.g., to get more details like image src
    public String getImageSrc() {
        return productContainer.findElement(By.cssSelector(".productinfo img")).getAttribute("src");
    }
}
