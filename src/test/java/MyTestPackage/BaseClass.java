package MyTestPackage;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseClass {
    AndroidDriver driver;

    @BeforeClass()
    public void SetUpAndroid() throws MalformedURLException {
        UiAutomator2Options options =new UiAutomator2Options();
//        options.setUdid("RKCY9014D2B");
        options.setDeviceName("emulator-5554");
        options.setApp("Downloads/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");
//        options.setPlatformVersion("16");
        options.setPlatformVersion("13");
        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        options.setAppPackage("com.swaglabsmobileapp");
        options.setAppActivity("com.swaglabsmobileapp.MainActivity");
        options.setNewCommandTimeout(Duration.ofSeconds(500000));
        driver=new AndroidDriver(new URL("http://127.0.0.1:4723"),options);
     //   utilities=new MyProjectLocators.Utilities(driver);
      //  utilities.OpenActivity("com.swaglabsmobileapp/com.swaglabsmobileapp.MainActivity");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));


        /* {
  "udid": "RKCY9014D2B",
  "deviceName": "emulator-5554" ,
  "app": "Downloads/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk",
  "platformVersion": "13",
  "platformName": "Android",
  "automationName": "UiAutomator2",
  "appPackage": "com.swaglabsmobileapp",
  "appActivity": "com.swaglabsmobileapp.MainActivity",
  "newCommandTimeout": 300
} */



    }

        @AfterClass
    public void Quit()
    {
        if(driver != null)
        {
            driver.quit();
        }
    }
}
