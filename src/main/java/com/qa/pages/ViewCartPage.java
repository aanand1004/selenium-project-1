package com.qa.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;

public class ViewCartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "table.table-condensed tbody tr")
    private List<WebElement> cartItemContainer;

    public ViewCartPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getCartItemContainer(){
        return cartItemContainer;
    }

    public cartItem getCartItemByIndex(int index){
        return new cartItem(driver, cartItemContainer.get(index));
    }

    public List<cartItem> getAllCartItems(){
        List<cartItem> allCartItems = new ArrayList<>();
        for (WebElement item : cartItemContainer) {
            cartItem cur_cart_item = new cartItem(driver, item);
            allCartItems.add(cur_cart_item);
        }
        return allCartItems;
    }

}
