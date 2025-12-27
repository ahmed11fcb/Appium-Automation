package MyProjectLocators;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class ThankYouPage {
    AndroidDriver driver;
public By ThankYouMessage = By.xpath("//android.widget.TextView[@text=\"THANK YOU FOR YOU ORDER\"]");
public By BackHomeButton= By.xpath("//android.view.ViewGroup[@content-desc=\"test-BACK HOME\"]");
    public ThankYouPage(AndroidDriver driver)
    {
        this.driver=driver;
    }

    public String GetThankYouMessage()
    {
        return driver.findElement(ThankYouMessage).getText();
    }

}
