package com.ecommerce.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By cartItems = By.className("cart_item");
    private By cartItemNames = By.className("inventory_item_name");
    private By checkoutButton = By.id("checkout");
    private By continueShoppingButton = By.id("continue-shopping");
    private By removeButton = By.xpath("//button[contains(text(),'Remove')]");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public int getCartItemCount() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartItems));
        List<WebElement> items = driver.findElements(cartItems);
        return items.size();
    }

    public boolean isCartPageDisplayed() {
        return driver.getCurrentUrl().contains("cart.html");
    }

    // IMPORTANT METHOD (missing earlier)
    public boolean isProductInCart(String productName) {

        List<WebElement> items = driver.findElements(cartItemNames);

        for (WebElement item : items) {
            if (item.getText().equalsIgnoreCase(productName)) {
                return true;
            }
        }

        return false;
    }

    public void proceedToCheckout() {

        WebElement checkoutBtn = wait.until(
                ExpectedConditions.presenceOfElementLocated(checkoutButton));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", checkoutBtn);

        wait.until(ExpectedConditions.visibilityOf(checkoutBtn));
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn));

        try {
            checkoutBtn.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkoutBtn);
        }
    }

    public void clickContinueShopping() {
        WebElement continueBtn = wait.until(
                ExpectedConditions.elementToBeClickable(continueShoppingButton));
        continueBtn.click();
    }

    public void removeProduct() {
        WebElement removeBtn = wait.until(
                ExpectedConditions.elementToBeClickable(removeButton));
        removeBtn.click();
    }
}
