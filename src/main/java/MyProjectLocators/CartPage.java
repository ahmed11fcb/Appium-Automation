package MyProjectLocators;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CartPage {
    AndroidDriver driver;
    public final By Continue = By.xpath("//android.widget.TextView[@text=\"CONTINUE SHOPPING\"]");
    public final By Checkout = By.xpath("//android.widget.TextView[@text=\"CHECKOUT\"]");
    public final By CartProducts = By.xpath("//android.view.ViewGroup[@content-desc=\"test-Item\"]");
    public final By Remove = By.xpath("//android.view.ViewGroup[@content-desc=\"test-REMOVE\"]");
    public final By CartKeyword = By.xpath("//android.widget.TextView[@text != '']");
    List<WebElement> AllCartProducts;
    Utility utilities;
    WebDriverWait wait;
    public CartPage(AndroidDriver driver) {
        this.driver = driver;
        utilities = new Utility(driver);
        wait=new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    public void SetContinue() {
        driver.findElement(Continue);
    }

    public void SetCheckout() {
        driver.findElement(Checkout);
    }

    public void SetCartProducts() {
        AllCartProducts = new ArrayList<>();
        AllCartProducts = driver.findElements(CartProducts);
    }

    public String GetCartKeyword()
    {
        return driver.findElement(CartKeyword).getText();
    }

    public WebElement GetProduct(String name_of_product) {
        WebElement Target = null;
        String Str = String.format(".//android.widget.TextView[@text=\"%s\"]",name_of_product);
        By Element_Name = By.xpath(Str);
        SetCartProducts();
        try {

            for (WebElement element : AllCartProducts) {

                if (element.findElement(Element_Name).getText().equals(name_of_product)) {
                    Target = element;
                    break;
                }
            }
        } catch (Exception e) {
//            utilities.ScrollToElementDownward(name_of_product);
            utilities.KeepScroll();
            Target=GetProduct(name_of_product);
        }
        return Target;
    }

    public int GetProductsCartNumber() {
        return AllCartProducts.size();
    }

    public void RemoveElementFromCart(String nameOfProduct)
    {
        WebElement target = GetProduct(nameOfProduct);
        target.findElement(Remove).click();
    }
    public void RemoveElementFromCartBySwiping(String nameOfProduct)
    {
        WebElement target = GetProduct(nameOfProduct);
        utilities.Swipe(target,"left",0.5);
        By TrashIcon= By.xpath("//android.widget.TextView[@text=\"\uDB82\uDE7A\"]");
        driver.findElement(TrashIcon).click();
    }
    public void ClickOnContinueButton()
    {
        utilities.ScrollToElementDownward("CHECKOUT");
        utilities.ClickOnElement(driver.findElement(Checkout));
    }
}