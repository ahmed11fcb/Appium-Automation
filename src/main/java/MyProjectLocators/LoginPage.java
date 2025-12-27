package MyProjectLocators;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    AndroidDriver driver;
    WebElement Username;
    WebElement Password;
    WebElement Login_btn;
    WebElement SwagLab_keyword;
    WebElement Error_message;

    public LoginPage(AndroidDriver driver)
    {
        this.driver=driver;
        Username=driver.findElement(AppiumBy.accessibilityId("test-Username"));
        Password=driver.findElement(AppiumBy.accessibilityId("test-Password"));
        Login_btn=driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"LOGIN\"]"));
        SwagLab_keyword=driver.findElement(AppiumBy.xpath("//android.widget.ScrollView[@content-desc=\"test-Login\"]/android.view.ViewGroup/android.widget.ImageView[1]"));
    }

    public WebElement getPassword() {
        return Password;
    }

    public WebElement getLogin_btn() {
        return Login_btn;
    }

    public WebElement getSwagLab_keyword() {
        return SwagLab_keyword;
    }

    public WebElement getUsername() {
        return Username;
    }

    public void SetErrorMessage()
    {
        Error_message=driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Username and password do not match any user in this service.\"]"));
    }
    public String GetErrorMessage()
    {
        return Error_message.getText();
    }
}
