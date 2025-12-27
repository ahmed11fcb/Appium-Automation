package MyTestPackage;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import MyProjectLocators.*;


public class ConfirmationPageTests extends BaseClass{
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;
    Utility utilities;
    CheckInformation checkInformation;
    ConfirmationPage confirmationPage;
    ThankYouPage thankYouPage;
    SoftAssert softAssert;

    @BeforeMethod
    public void SetupBeforeConfirmationPageTests()
    {
        loginPage = new LoginPage(driver);
        utilities=new Utility(driver);
        utilities.SendKeysToElement(loginPage.getUsername(), "standard_user");
        utilities.SendKeysToElement(loginPage.getPassword(), "secret_sauce");
        utilities.ClickOnElement(loginPage.getLogin_btn());
        productsPage=new ProductsPage(driver);
        productsPage.AddProduct("Test.allTheThings() T-Shirt (Red)");
        productsPage.AddProduct("Sauce Labs Bolt T-Shirt");
        productsPage.AddProduct("Sauce Labs Backpack");
        productsPage.SetCart();
        utilities.ClickOnElement(productsPage.CartIcon);
        cartPage=new CartPage(driver);
        cartPage.ClickOnContinueButton();
        checkInformation=new CheckInformation(driver);
        utilities.SendKeysToElement(driver.findElement(checkInformation.FirstName),"Ahmed");
        utilities.SendKeysToElement(driver.findElement(checkInformation.LastName),"Hamdy");
        utilities.SendKeysToElement(driver.findElement(checkInformation.ZipCode),"11311");
        utilities.ClickOnElement(driver.findElement(checkInformation.ContinueButton));
        confirmationPage=new ConfirmationPage(driver);
        softAssert=new SoftAssert();
    }

    @Test
    public void TestTotalPrice()
    {
        double number=confirmationPage.CalculateTotalProductsPrice();
        double number2=confirmationPage.GetTotalPriceWithoutTaxs();
        Assert.assertEquals(number , number2);
    }

    @Test
    public void TestTotalTaxs()
    {
        double number = confirmationPage.CalculateTotalProductsPrice();
        double number2=confirmationPage.GetTaxs();
        Assert.assertEquals(number2,Math.round((number * 0.08)*100.0)/100.0);
    }


    @Test
    public void TestTotalPriceWithTaxs()
    {
        double number =confirmationPage.CalculateTotalProductsPrice();
        double number2 = confirmationPage.GetTaxs();
        double number3 = number+number2;
        Assert.assertEquals(confirmationPage.GetTotalPriceWithTaxs() ,number3);
    }

    @Test
    public void TestConfirmOrder()
    {
        double TotalPrice= confirmationPage.CalculateTotalProductsPrice();
        softAssert.assertEquals(confirmationPage.GetTotalPriceWithoutTaxs(),TotalPrice);
        softAssert.assertEquals(confirmationPage.GetTaxs(),Math.round((TotalPrice*0.08)*100.0)/100.0);
        softAssert.assertEquals(confirmationPage.GetTotalPriceWithTaxs(),Math.round((TotalPrice*0.08)*100.0)/100.0 + TotalPrice);

        confirmationPage.ClickOmFinish();
        thankYouPage=new ThankYouPage(driver);

        softAssert.assertEquals(thankYouPage.GetThankYouMessage(),"THANK YOU FOR YOU ORDER");
        utilities.ClickOnElement(driver.findElement(thankYouPage.BackHomeButton));

    }
    @AfterMethod
    public void ClearTest()
    {
        driver.terminateApp("com.swaglabsmobileapp");
        driver.activateApp("com.swaglabsmobileapp");
    }
}
