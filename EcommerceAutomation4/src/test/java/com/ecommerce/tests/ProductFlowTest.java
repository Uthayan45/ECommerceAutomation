package com.ecommerce.tests;

import com.ecommerce.base.BaseTest;
import com.ecommerce.pages.CartPage;
import com.ecommerce.pages.CheckoutPage;
import com.ecommerce.pages.HomePage;
import com.ecommerce.pages.LoginPage;
import com.ecommerce.pages.SearchPage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductFlowTest extends BaseTest {

    @Test
    public void testSearchAndFilter() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageDisplayed(), "Home page not loaded!");

        SearchPage searchPage = new SearchPage(driver);
        searchPage.applyFilter("Price (high to low)");

        int productCount = searchPage.getProductCount();
        Assert.assertTrue(productCount > 0, "No products found after applying filter!");
    }

    @Test
    public void testAddToCart() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageDisplayed(), "Home page not loaded!");

        SearchPage searchPage = new SearchPage(driver);
        String productName = "Sauce Labs Backpack";

        Assert.assertTrue(searchPage.isProductDisplayed(productName), "Product not found!");
        searchPage.addToCart(productName);

        homePage.goToCart();

        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isCartPageDisplayed(), "Cart page not displayed!");
        Assert.assertTrue(cartPage.isProductInCart(productName), "Product not in cart!");
    }

    @Test
    public void testCompletePurchase() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageDisplayed(), "Home page not loaded!");

        SearchPage searchPage = new SearchPage(driver);
        String productName = "Sauce Labs Backpack";

        Assert.assertTrue(searchPage.isProductDisplayed(productName), "Product not found!");
        searchPage.addToCart(productName);

        homePage.goToCart();

        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isCartPageDisplayed(), "Cart page not displayed!");
        Assert.assertTrue(cartPage.isProductInCart(productName), "Product not in cart!");

        cartPage.proceedToCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterCheckoutDetails("John", "Doe", "12345");
        checkoutPage.finishCheckout();

        Assert.assertTrue(checkoutPage.isCheckoutSuccessful(), "Checkout failed!");
    }
}
