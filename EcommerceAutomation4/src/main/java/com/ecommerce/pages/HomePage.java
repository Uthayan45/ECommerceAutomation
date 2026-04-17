package com.ecommerce.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By cartIcon = By.className("shopping_cart_link");
    private By productTitle = By.className("title");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public boolean isHomePageDisplayed() {

        WebElement title = wait.until(
                ExpectedConditions.visibilityOfElementLocated(productTitle));

        return title.getText().equalsIgnoreCase("Products");
    }

    public void goToCart() {

        WebElement cart = wait.until(
                ExpectedConditions.visibilityOfElementLocated(cartIcon));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);", cart);

        wait.until(ExpectedConditions.elementToBeClickable(cart));

        try {
            cart.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cart);
        }

        wait.until(ExpectedConditions.urlContains("cart.html"));
    }
}
