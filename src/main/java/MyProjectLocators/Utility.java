package MyProjectLocators;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

public class Utility {
    AndroidDriver driver;
    WebDriverWait wait;
    DeviceRotation deviceRotation;

    public Utility(AndroidDriver driver)
    {
        this.driver=driver;
        wait=new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void OpenActivity(String IntentOfApp)
    {
        ((JavascriptExecutor)driver).executeScript("mobile: startActivity", Map.of("intent",IntentOfApp));
    }

    public void SendKeysToElement(WebElement element , String keys)
    {
        element.sendKeys(keys);
    }
    public void ClickOnElement(WebElement element)
    {
        element.click();
    }
    public String GetTextOFElement(By Locator)
    {
        return driver.findElement(Locator).getText();
    }

    public void ClearTextField(By Locator)
    {
        driver.findElement(Locator).clear();
    }

    public void waitForElement(By Locator)
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(Locator));
    }
    public void ScrollToElementDownward(String name_Element)
    {
        String script=String.format("new UiScrollable(new UiSelector()).scrollIntoView(text(\"%s\"));",name_Element);
        driver.findElement(AppiumBy.androidUIAutomator(script));
    }
   public void ScrollToElementUpward(String  name_Element)
   {

       String script = String.format(
               "new UiScrollable(new UiSelector().scrollable(true).instance(0))" +
                       ".setAsVerticalList().scrollBackward().scrollIntoView(new UiSelector().text(\"%s\"));",
               name_Element
       );

       driver.findElement(AppiumBy.androidUIAutomator(script));


   }

    public void Swipe(WebElement element, String direction,double percent)
    {
        ((JavascriptExecutor)driver).executeScript("mobile: swipeGesture",
                Map.of("elementId",((RemoteWebElement)element).getId(),
                        "direction",direction,"percent",percent));

    }

    public boolean KeepScroll()
    {
        // Java
        boolean canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript(
                "mobile: scrollGesture",
                Map.of(
                        "left", 0,
                        "top", 0,
                        "width", 1080,
                        "height", 1920,
                        "direction", "down",
                        "percent", 0.5,
                        "speed",1000
                )
        );


        return canScrollMore;
    }

    public void RotateToLandScape()
    {
       deviceRotation=new DeviceRotation(0,0,90);
       driver.rotate(deviceRotation);
    }
    public void RotateToPortrait()
    {
        deviceRotation=new DeviceRotation(0,0,0);
        driver.rotate(deviceRotation);
    }


    }


