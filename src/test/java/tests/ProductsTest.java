package tests;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import io.qameta.allure.*;
public class ProductsTest extends BaseTest{

    @Epic("Модуль корзины")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Добавление товаров в корзину")
    @Test
    public void addProductItem(){
        loginPage.open()
                        .login(user, password);

        productsPage.addPositionToCart(3)
                .goToCart();

        assertEquals(productsPage.checkItemDivCart(), 3);
    }

    @Epic("Модуль корзины")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка удаления товара из корзины")
    @Test
    public void removeItem(){

        loginPage.open()
                .login(user, password);

        productsPage.addPositionToCart(2)
                .goToCart()
                .removeItem(1);

        assertEquals(productsPage.checkItemDivCart(), 1);
    }

    @Epic("Модуль корзины")
    @Severity(SeverityLevel.MINOR)
    @Description("Проверка входа в систему с корректными данными")
    @Test
    public void countMainPageItemAdd(){

        loginPage.open()
                .login(user, password);

        productsPage.addPositionToCart(3);

        assertEquals(productsPage.checkCartBadge(), 3);
    }
}


