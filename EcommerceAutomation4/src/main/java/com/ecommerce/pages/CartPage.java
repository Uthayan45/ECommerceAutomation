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
    private By removeButtons = By.xpath("//button[contains(text(),'Remove')]");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public boolean isCartPageDisplayed() {
        wait.until(ExpectedConditions.urlContains("cart"));
        return driver.getCurrentUrl().contains("cart");
    }

    public int getCartItemCount() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartItems));
        List<WebElement> items = driver.findElements(cartItems);
        return items.size();
    }

    public boolean isProductInCart(String productName) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(cartItems));

        List<WebElement> products = driver.findElements(cartItemNames);

        for (WebElement product : products) {

            String name = product.getText().trim();

            if (name.equalsIgnoreCase(productName.trim())) {
                return true;
            }
        }

        return false;
    }

    public void proceedToCheckout() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutButton));

        WebElement checkoutBtn = driver.findElement(checkoutButton);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", checkoutBtn);

        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn));

        checkoutBtn.click();
    }

    public void clickContinueShopping() {

        WebElement continueBtn = wait.until(
                ExpectedConditions.elementToBeClickable(continueShoppingButton));

        continueBtn.click();
    }

    public void removeProduct() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(cartItems));

        List<WebElement> removeBtn = driver.findElements(removeButtons);

        if (!removeBtn.isEmpty()) {

            WebElement btn = removeBtn.get(0);

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView(true);", btn);

            wait.until(ExpectedConditions.elementToBeClickable(btn));

            btn.click();
        }
    }
}
