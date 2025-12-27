package MyTestPackage;

import org.openqa.selenium.ScreenOrientation;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import MyProjectLocators.*;


public class TestProductsPage extends  BaseClass{

    Utility utilities;
    LoginPage loginPage;
    ProductsPage productsPage;
    SoftAssert softAssert;
    @BeforeMethod
    public void SetUpBeforeTestProductsPage()
    {
        utilities=new Utility(driver);
        loginPage=new LoginPage(driver);
        utilities.SendKeysToElement(loginPage.getUsername(),"standard_user");
        utilities.SendKeysToElement(loginPage.getPassword(),"secret_sauce");
        utilities.ClickOnElement(loginPage.getLogin_btn());
        softAssert=new SoftAssert();
    }

    @Test
    public void TestProductsAddedToList() throws InterruptedException {
        productsPage=new ProductsPage(driver);
        productsPage.AddProduct("Sauce Labs Onesie");
        productsPage.AddProduct("Sauce Labs Bolt T-Shirt");
        productsPage.AddProduct("Sauce Labs Backpack");
        productsPage.SetCart();
        Assert.assertEquals(productsPage.GetIconNumber(),3);

    }
    @Test
    public void TestAdd_1_ProducttoCart()
    {
        productsPage=new ProductsPage(driver);
        productsPage.AddProduct("Sauce Labs Onesie");
        productsPage.SetCart();
        Assert.assertEquals(productsPage.GetIconNumber(),1);
    }
    @Test
    public void Test_2_ProductsAddedToList() throws InterruptedException {
        productsPage=new ProductsPage(driver);
        productsPage.AddProduct("Sauce Labs Onesie");
        productsPage.AddProduct("Sauce Labs Bolt T-Shirt");
        productsPage.SetCart();
        Assert.assertEquals(productsPage.GetIconNumber(),2);

    }

    @Test
    public void Validate_ADDToCartButtonConversion_And_CartConversion()
    {
        productsPage=new ProductsPage(driver);
        boolean flag=productsPage.CheckButtonConversion("Sauce Labs Onesie");
        softAssert.assertEquals(flag,true);
        productsPage.SetCart();
        softAssert.assertEquals(productsPage.GetIconNumber(),1);
    }


    @AfterMethod
    public void ClearTest()
    {
        driver.terminateApp("com.swaglabsmobileapp");
        driver.activateApp("com.swaglabsmobileapp");
    }
}
