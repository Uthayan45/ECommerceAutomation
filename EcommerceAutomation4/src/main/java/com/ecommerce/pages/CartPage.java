package com.ecommerce.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By cartItems = By.className("cart_item");
    private By cartItemNames = By.className("inventory_item_name");
    private By checkoutButton = By.id("checkout");
    private By continueShoppingButton = By.id("continue-shopping");
    private By removeButtons = By.xpath("//button[contains(text(),'Remove')]");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public boolean isCartPageDisplayed() {
        wait.until(ExpectedConditions.urlContains("cart"));
        return driver.getCurrentUrl().contains("cart");
    }

    public int getCartItemCount() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(cartItems));
        List<WebElement> items = driver.findElements(cartItems);
        return items.size();
    }

    public boolean isProductInCart(String productName) {

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(cartItems));

        List<WebElement> items = driver.findElements(cartItemNames);

        for (WebElement item : items) {
            if (item.getText().trim().equalsIgnoreCase(productName.trim())) {
                return true;
            }
        }

        return false;
    }

    public void proceedToCheckout() {

        wait.until(ExpectedConditions.urlContains("cart"));

        WebElement checkoutBtn = wait.until(
                ExpectedConditions.presenceOfElementLocated(checkoutButton));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", checkoutBtn);

        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn));

        try {
            checkoutBtn.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();", checkoutBtn);
        }
    }

    public void clickContinueShopping() {

        WebElement continueBtn = wait.until(
                ExpectedConditions.elementToBeClickable(continueShoppingButton));

        continueBtn.click();
    }

    public void removeProduct() {

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(removeButtons));

        List<WebElement> buttons = driver.findElements(removeButtons);

        if (!buttons.isEmpty()) {
            buttons.get(0).click();
        }
    }
}
