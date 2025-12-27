package MyTestPackage;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import MyProjectLocators.*;


public class CheckInformationTests extends  BaseClass{

    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;
    Utility utilities;
    CheckInformation checkInformation;

    @BeforeMethod
    public void SetUpBeforeCheckInformationTests() throws InterruptedException {
        loginPage = new LoginPage(driver);
        utilities=new Utility(driver);
        utilities.SendKeysToElement(loginPage.getUsername(), "standard_user");
        utilities.SendKeysToElement(loginPage.getPassword(), "secret_sauce");
        utilities.ClickOnElement(loginPage.getLogin_btn());
        productsPage=new ProductsPage(driver);
        productsPage.AddProduct("Sauce Labs Backpack");
        productsPage.AddProduct("Sauce Labs Bike Light");
        productsPage.AddProduct("Sauce Labs Fleece Jacket");
        productsPage.SetCart();
        utilities.ClickOnElement(productsPage.CartIcon);
        cartPage=new CartPage(driver);
        cartPage.ClickOnContinueButton();
    }

    @Test
    public void EnterValidDataForCheckInformation()
    {
        checkInformation=new CheckInformation(driver);
        utilities.SendKeysToElement(driver.findElement(checkInformation.FirstName),"Ahmed");
        utilities.SendKeysToElement(driver.findElement(checkInformation.LastName),"Hamdy");
        utilities.SendKeysToElement(driver.findElement(checkInformation.ZipCode),"11311");
        utilities.ClickOnElement(driver.findElement(checkInformation.ContinueButton));

    }
    @Test
    public void EnterInValidDataForCheckInformation()
    {
        checkInformation=new CheckInformation(driver);
        utilities.SendKeysToElement(driver.findElement(checkInformation.FirstName),"%%$$^7");
        utilities.SendKeysToElement(driver.findElement(checkInformation.LastName),"1234");
        utilities.SendKeysToElement(driver.findElement(checkInformation.ZipCode),"11311");
        utilities.ClickOnElement(driver.findElement(checkInformation.ContinueButton));
        utilities.waitForElement(checkInformation.CheckInformation);
        Assert.assertEquals(checkInformation.GetCheckInfromationText(),"CHECKOUT: INFORMATION");

    }

    @AfterMethod
    public void ClearTest()
    {
        driver.terminateApp("com.swaglabsmobileapp");
        driver.activateApp("com.swaglabsmobileapp");
    }

}
