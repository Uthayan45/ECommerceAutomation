package com.ecommerce.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By productsTitle = By.className("title");
    private By cartIcon = By.cssSelector(".shopping_cart_link");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public boolean isHomePageDisplayed() {
        WebElement title = wait.until(
                ExpectedConditions.visibilityOfElementLocated(productsTitle));
        return title.getText().trim().equalsIgnoreCase("Products");
    }

    public void goToCart() {
        WebElement cart = wait.until(
                ExpectedConditions.presenceOfElementLocated(cartIcon));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", cart);

        wait.until(ExpectedConditions.visibilityOf(cart));
        wait.until(ExpectedConditions.elementToBeClickable(cart));

        try {
            new Actions(driver).moveToElement(cart).click().perform();
        } catch (Exception e) {
            try {
                cart.click();
            } catch (Exception ex) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cart);
            }
        }

        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("cart.html"),
                ExpectedConditions.urlContains("cart")
        ));
    }
}
