package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.qameta.allure.*;
import java.util.List;

public class ProductsPage extends BasePage{

    private static final By BUTTON_ADD_CART = By.xpath("//button[contains(text(), 'Add to cart')]");
    private static final By ICON_CART = By.xpath("//a[@class='shopping_cart_link']");
    private static final By CART_BADGE = By.xpath("//span[@class='shopping_cart_badge']");
    private static final By ITEM_DIV_IN_CART = By.xpath("//div[@class='cart_item']");
    private static final By REMOVE_BUTTON_IN_CART = By.xpath("//button[contains(text(), 'Remove')]");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Добавление товара в корзину")
    public ProductsPage addPositionToCart(int howMuch){
        List<WebElement> positions = wait.until(ExpectedConditions.elementToBeClickable(BUTTON_ADD_CART)).findElements(BUTTON_ADD_CART);
        int count = 0;
        for(WebElement position: positions){
            position.click();
            count ++;
            if(count==howMuch){
                break;
            }
        }

        return this;
    }

    @Step("Переход в корзину")
    public ProductsPage goToCart(){
        driver.findElement(ICON_CART).click();
        return this;
    }

    @Description("Проверка количества товаров в ")
    public int checkItemDivCart(){
        List<WebElement> divs = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ITEM_DIV_IN_CART));
        return divs.size();
    }

    public int checkCartBadge(){
        WebElement cartBadge = driver.findElement(CART_BADGE);
        String badgeText = cartBadge.getText();
        return Integer.parseInt(badgeText.trim());
    }

    @Step("Удаление товаров из корзины")
    public void removeItem(int homMuch){
        List<WebElement> elements = wait.until(ExpectedConditions.elementToBeClickable(REMOVE_BUTTON_IN_CART)).findElements(REMOVE_BUTTON_IN_CART);
        int count = 0;
        for(WebElement element: elements){
            element.click();
            count ++;
            if(count==homMuch){
                break;
            }
        }
    }
}
