package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.qameta.allure.*;
import utils.PropertyReader;


import static org.testng.Assert.assertEquals;
import static pages.BasePage.BASE_URL;

public class LoginTest extends BaseTest {

    @Epic("Модуль авторизации магазина")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Проверка входа в систему с корректными данными")
    @Test
    public void correctLogin() {
        loginPage
                .open()
                .login(user, password);
        assertEquals(driver.getCurrentUrl(), BASE_URL + "inventory.html");
    }

    @Epic("Модуль авторизации магазина")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка входа в систему с данными  заблокированного пользователя")
    @Test
    public void lockedLogin() {
        loginPage
                .open()
                .login("locked_out_user", password);
        assertEquals(loginPage.errorAlertText(), "Epic sadface: Sorry, this user has been locked out.");
    }

    @DataProvider(name = "loginOrPasswordData")
    public Object[][] loginOrPasswordData() {
        String user = PropertyReader.getProperty("sauce.user");
        String password = PropertyReader.getProperty("sauce.password");
        return new Object[][]{
                {"", password, "Epic sadface: Username is required"},
                {user, "", "Epic sadface: Password is required"}
        };
    }

    @Epic("Модуль авторизации магазина")
    @Severity(SeverityLevel.NORMAL)
    @Description("Проверка входа в систему с некорректными данными")
    @Test(dataProvider = "loginOrPasswordData")
    public void checkErrorAuth(String user, String pass, String exception){
        loginPage
                .open()
                .login(user,pass);
        assertEquals(loginPage.errorAlertText(), exception);
    }
}
