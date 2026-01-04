package com.qa.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SigninPage {
    private WebDriver driver;
    private WebElement emailField;
    private WebElement passwordField;
    private WebElement loginButton;

public SigninPage(WebDriver driver){
    this.driver = driver;
}

public void signin(String email, String password){
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
    passwordField = driver.findElement(By.xpath("//input[@placeholder='Password']"));
    loginButton = driver.findElement(By.xpath("//button[normalize-space()='Login']"));
    emailField.sendKeys(email);
    passwordField.sendKeys(password);
    loginButton.click();
}

public void clearemailField(){
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
    emailField.clear();
}

public void clearpwdField(){
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Password']")));
    passwordField.clear();
}
    
}
