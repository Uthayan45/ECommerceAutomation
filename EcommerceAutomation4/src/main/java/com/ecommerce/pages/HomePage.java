package com.ecommerce.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By productsTitle = By.className("title");
    private By cartIcon = By.className("shopping_cart_link");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public boolean isHomePageDisplayed() {

        WebElement title = wait.until(
                ExpectedConditions.visibilityOfElementLocated(productsTitle));

        return title.getText().equalsIgnoreCase("Products");
    }

    public void goToCart() {

        WebElement cart = wait.until(
                ExpectedConditions.elementToBeClickable(cartIcon));

        cart.click();

        wait.until(ExpectedConditions.urlContains("cart"));
    }
}
