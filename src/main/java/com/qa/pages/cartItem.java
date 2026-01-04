package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class cartItem {
    private WebDriver driver;
    private WebElement cartItem;

    //Locators relative to cartItem 
    private By itemName = By.cssSelector("td.cart_description h4 a");
    private By price = By.cssSelector(  "td.cart_price p");
    private By quantity = By.cssSelector("td.cart_quantity button");
    private By totalPrice = By.cssSelector("td.cart_total p.cart_total_price");

    public cartItem(WebDriver driver, WebElement cartItem){
        this.driver = driver;
        this.cartItem = cartItem;
    }

    public String getItemName(){
        return cartItem.findElement(itemName).getText();
    }

    public String getPrice(){
        return cartItem.findElement(price).getText();
    }

    public String getQuantity(){
        return cartItem.findElement(quantity).getText();
    }

    public String getTotalPrice(){
        return cartItem.findElement(totalPrice).getText();
    }



    
}
