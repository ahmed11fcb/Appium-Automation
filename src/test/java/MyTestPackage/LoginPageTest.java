package MyTestPackage;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import MyProjectLocators.*;

import javax.swing.text.Utilities;

public class LoginPageTest extends BaseClass {
    Utility utilities;
    LoginPage loginPage;
    SoftAssert softAssert;

    @BeforeMethod
    public void beforeMethodStarts()
    {
        utilities=new Utility(driver);
        loginPage=new LoginPage(driver);
        softAssert=new SoftAssert();
    }
    @Test
    public void TestLoginWithValidData()
    {
        utilities.SendKeysToElement(loginPage.getUsername(),"standard_user");
        utilities.SendKeysToElement(loginPage.getPassword(),"secret_sauce");
        utilities.ClickOnElement(loginPage.getLogin_btn());
    }
    @Test
    public void TestLoginWithInvalidData()
    {
        utilities.SendKeysToElement(loginPage.getUsername(),"ahmed123");
        utilities.SendKeysToElement(loginPage.getPassword(),"123");
        utilities.ClickOnElement(loginPage.getLogin_btn());
        loginPage.SetErrorMessage();
        softAssert.assertEquals(loginPage.GetErrorMessage(),"Username and password do not match any user in this service.");

    }

    @AfterMethod
    public void ClearTest()
    {
        driver.terminateApp("com.swaglabsmobileapp");
        driver.activateApp("com.swaglabsmobileapp");
    }
}
