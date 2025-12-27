package MyProjectLocators;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductsPage {
    AndroidDriver driver;
    WebDriverWait wait;
    By Products_Keyword= By.xpath("//android.widget.TextView[@text=\"PRODUCTS\"]");
    By AddToCart=By.xpath(".//android.widget.TextView[@text=\"ADD TO CART\"]");
    By Remove=By.xpath(".//android.view.ViewGroup[@content-desc=\"test-REMOVE\"]");
    List<WebElement> All_Products;
    Utility utilities;
   public WebElement CartIcon;

    public ProductsPage(AndroidDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        utilities = new Utility(driver);

    }

    public void SetAllProducts() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-Item\"]/android.view.ViewGroup"))));
        All_Products = driver.findElements(AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-Item\"]/android.view.ViewGroup"));
    }
    public String GetProductsPageText()
    {
        return driver.findElement(Products_Keyword).getText();
    }


    public void AddProduct(String NameOFProduct)
    {
        boolean clicked =false;

        utilities.ScrollToElementDownward(NameOFProduct);
        String Str=String.format("//android.widget.TextView[@content-desc=\"test-Item title\" and @text=\"%s\"]",NameOFProduct);
        try
        {
            if(driver.findElement(By.xpath(Str)).isDisplayed())
            {
                SetAllProducts();
            }
        }
        catch (Exception e)
        {
            utilities.ScrollToElementDownward(NameOFProduct);
        }

        for(WebElement element : All_Products)
        {
            WebElement Target=null;
            try{
                 Target=element.findElement(AppiumBy.xpath(Str));

                if(Target.getText().equals(NameOFProduct))
                {
                    WebElement addtocart=element.findElement(AddToCart);
                    addtocart.click();
                    clicked=true;
                    break;
                }
            }catch (Exception exception){
                if(clicked)
                {
                    break;
                }
            }



        }

        while (true)
        {
            utilities.ScrollToElementUpward("Sauce Labs Backpack");
            if(driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"test-Item title\" and @text=\"Sauce Labs Backpack\"]")).isDisplayed())
            {
                break;
            }
        }

    }

    public boolean CheckButtonConversion(String nameofProduct)
    {
        SetAllProducts();
        boolean Flag=false;
        int startProduct=0;
        String Str=String.format(".//android.widget.TextView[@content-desc=\"test-Item title\" and @text=\"%s\"]",nameofProduct);
        By NameofProduct= By.xpath(Str);
        while(!Flag) {

                for (int i=startProduct; i < All_Products.size();i++) {
                    try {
                        if (All_Products.get(i).findElement(NameofProduct).getText().equals(nameofProduct)) {
                            All_Products.get(i).findElement(AddToCart).click();
                            Flag = All_Products.get(i).findElement(Remove).isDisplayed();
                            break;
                        }

                    } catch (Exception exception) {

                    }

                }
            if(!Flag)
            {
                utilities.KeepScroll();
                SetAllProducts();
                startProduct=2;
            }
        }

        return Flag;
    }

public void SetCart()
{
    CartIcon=driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-Cart\"]"));
}
    public int GetIconNumber()
    {
        By CartNumber = AppiumBy.xpath(".//android.widget.TextView");
        String NumberOfProductsInCart=CartIcon.findElement(CartNumber).getText();

        return Integer.parseInt(NumberOfProductsInCart);
    }



}
