package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProductsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locator for all product containers as a list of WebElements
    @FindBy(xpath = "//div[@class='features_items']//div[@class='col-sm-4']") // Targets each product grid item
    private List<WebElement> productContainers;

    // Optional: Categories sidebar (if needed for tests)
    @FindBy(id = "accordian")
    private WebElement categoriesSidebar;

    // Optional: Search bar (if needed for tests)
    @FindBy(id = "search_product")
    private WebElement searchBar;

    @FindBy(xpath = "//button[@class='btn btn-success close-modal btn-block']")
    private WebElement continueShoppingButton;

    @FindBy(xpath = "//u[normalize-space()='View Cart']")
    private WebElement viewCartButton;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Initialize @FindBy elements
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // Get the raw list of product container WebElements
    public List<WebElement> getProductContainers() {
        return productContainers;
    }

    // Get a list of Product objects (recommended for accessing details)
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        for (WebElement container : productContainers) {
            products.add(new Product(driver, container));
        }
        return products;
    }

    // Example method: Get all product names as a list of strings
    public List<String> getAllProductNames() {
        List<String> names = new ArrayList<>();
        for (Product product : getAllProducts()) {
            names.add(product.getName());
        }
        return names;
    }

    // Example method: Get product by index
    public Product getProductByIndex(int index) {
        if (index >= 0 && index < productContainers.size()) {
            return new Product(driver, productContainers.get(index));
        }
        throw new IllegalArgumentException("Invalid product index: " + index);
    }

    public void clickContinueShopping() {
        wait.until(ExpectedConditions.elementToBeClickable(continueShoppingButton));
        continueShoppingButton.click();
    }

     public void clickViewCart() {
        wait.until(ExpectedConditions.elementToBeClickable(viewCartButton));
        viewCartButton.click();
    }
}