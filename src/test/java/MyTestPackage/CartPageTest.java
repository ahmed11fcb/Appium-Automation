package MyTestPackage;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import MyProjectLocators.*;


public class CartPageTest extends BaseClass{

    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;
    Utility utilities;


    @BeforeMethod
    public void SetUpBeforeMethod() {
        loginPage = new LoginPage(driver);
        utilities=new Utility(driver);
        utilities.SendKeysToElement(loginPage.getUsername(), "standard_user");
        utilities.SendKeysToElement(loginPage.getPassword(), "secret_sauce");
        utilities.ClickOnElement(loginPage.getLogin_btn());
        productsPage=new ProductsPage(driver);
    }

    @Test()
    public void Test_3_ProductsinCart() throws InterruptedException {
        productsPage.AddProduct("Sauce Labs Onesie");
        productsPage.AddProduct("Sauce Labs Bolt T-Shirt");
        productsPage.AddProduct("Sauce Labs Backpack");
        productsPage.SetCart();
        utilities.ClickOnElement(productsPage.CartIcon);
        cartPage=new CartPage(driver);
        cartPage.SetCartProducts();
        Assert.assertEquals(cartPage.GetProductsCartNumber(),4);
    }

    @Test
    public void Test_3_ProductsThenRemove_1()
    {
        productsPage.AddProduct("Sauce Labs Onesie");
        productsPage.AddProduct("Sauce Labs Bolt T-Shirt");
        productsPage.AddProduct("Sauce Labs Backpack");
        productsPage.SetCart();
        utilities.ClickOnElement(productsPage.CartIcon);
        cartPage=new CartPage(driver);
        cartPage.RemoveElementFromCart("Sauce Labs Backpack");
    }

    @Test
    public void Test_3_ProductsThenRemove_1_BySwipping()
    {
        productsPage.AddProduct("Sauce Labs Onesie");
        productsPage.AddProduct("Sauce Labs Bolt T-Shirt");
        productsPage.AddProduct("Sauce Labs Backpack");
        productsPage.SetCart();
        utilities.ClickOnElement(productsPage.CartIcon);
        cartPage=new CartPage(driver);
        cartPage.RemoveElementFromCartBySwiping("Sauce Labs Backpack");
    }
    @Test
    public void Test_Checkout()
    {
        productsPage.AddProduct("Sauce Labs Fleece Jacket");
        productsPage.AddProduct("Sauce Labs Bolt T-Shirt");
        productsPage.AddProduct("Sauce Labs Backpack");
        productsPage.AddProduct("Sauce Labs Bike Light");
        productsPage.SetCart();
        utilities.ClickOnElement(productsPage.CartIcon);
        cartPage=new CartPage(driver);
        cartPage.ClickOnContinueButton();
    }
    @Test
    public void Test_Checkout_withoutAddingProducts()  {
        productsPage.SetCart();
        utilities.ClickOnElement(productsPage.CartIcon);
        cartPage=new CartPage(driver);
        cartPage.ClickOnContinueButton();
        utilities.waitForElement(cartPage.CartKeyword);
        Assert.assertEquals(cartPage.GetCartKeyword(),"YOUR CART");
    }

    @AfterMethod
    public void ClearTest()
    {
        driver.terminateApp("com.swaglabsmobileapp");
        driver.activateApp("com.swaglabsmobileapp");
    }

}
