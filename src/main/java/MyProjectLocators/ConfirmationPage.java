package MyProjectLocators;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ConfirmationPage {
    AndroidDriver driver;
    Utility utilities;
    By EachProduct = By.xpath("//android.view.ViewGroup[@content-desc=\"test-Item\"]");
    By Product_Price = By.xpath(".//android.widget.TextView[contains(@text,'$')]");
    By ItemTotal = By.xpath("//android.widget.TextView[contains(@text,'Item total')]");
    By Tax = By.xpath("//android.widget.TextView[contains(@text,'Tax')]");
    By Total = By.xpath("//android.widget.TextView[contains(@text,'Total')]");
    By FinishButton=AppiumBy.androidUIAutomator("new UiSelector().text(\"FINISH\")");

    List<WebElement> AllProducts;

    public ConfirmationPage(AndroidDriver driver) {
        this.driver = driver;
        utilities=new Utility(driver);
        AllProducts = new ArrayList();
    }

    public void SetAllProducts() {
        AllProducts = driver.findElements(EachProduct);
    }


    public double CalculateTotalProductsPrice()
    {
        SetAllProducts();
        double TotalSum=0;
        int startProduct=0;
        while (true) {
            try {
                for (int i=startProduct;i<AllProducts.size();i++) {

                    String str = AllProducts.get(startProduct).findElement(Product_Price).getText();
                    str = str.substring(str.indexOf('$') + 1);
                    TotalSum += Double.parseDouble(str);
                    startProduct++;
                }
                break;

            } catch (Exception e) {
                utilities.KeepScroll();
            }
        }
        return TotalSum;
    }

    public double GetTotalPriceWithoutTaxs() {
        String TotalWithoutTaxs="";

            while (true)
            {
                try {
                    TotalWithoutTaxs = driver.findElement(ItemTotal).getText();
                    break;
                }catch (Exception e) {
                utilities.KeepScroll();
            }
            }


        TotalWithoutTaxs=TotalWithoutTaxs.substring(TotalWithoutTaxs.indexOf('$')+1);

        return Double.parseDouble(TotalWithoutTaxs);


    }


    public double GetTaxs() {
        String Taxs="";

            while (true)
            {
                try {
                Taxs = driver.findElement(Tax).getText();
                break;
                } catch (Exception e) {
                    utilities.KeepScroll();
                }
            }


        Taxs=Taxs.substring(Taxs.indexOf('$')+1);

        return Double.parseDouble(Taxs);


    }
    public double GetTotalPriceWithTaxs() {
        String TotalWithTaxs="";

            while (true)
            {
                try {
                TotalWithTaxs = driver.findElement(Total).getText();
                break;
                } catch (Exception e) {
                    utilities.KeepScroll();
                }
            }


        TotalWithTaxs=TotalWithTaxs.substring(TotalWithTaxs.indexOf('$')+1);

        return Double.parseDouble(TotalWithTaxs);

    }
    public void ClickOmFinish()
    {
        while (true)
        {
            try{
                utilities.ClickOnElement(driver.findElement(FinishButton));
                break;
            }catch (Exception e)
            {
                utilities.KeepScroll();
            }
        }
    }


}
