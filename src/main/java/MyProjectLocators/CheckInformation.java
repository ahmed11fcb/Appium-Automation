package MyProjectLocators;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class CheckInformation {
    AndroidDriver driver;

    public final By FirstName = AppiumBy.accessibilityId("test-First Name");
    public final By LastName=AppiumBy.accessibilityId("test-Last Name");
    public final By ZipCode = AppiumBy.accessibilityId("test-Zip/Postal Code");
    public final By ContinueButton = AppiumBy.androidUIAutomator("new UiSelector().text(\"CONTINUE\")");
    public final By CheckInformation=By.xpath("//android.widget.TextView[@text != '']");
    public CheckInformation(AndroidDriver driver)
    {
        this.driver=driver;
    }

    public String GetCheckInfromationText()
    {
        return driver.findElement(CheckInformation).getText();
    }
}
