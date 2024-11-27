package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static pages.BasePage.BASE_URL;

public class LoginTest extends BaseTest {
    @Test
    public void correctLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(BASE_URL + "inventory.html", driver.getCurrentUrl());
    }

    @Test
    public void lockedLogin() {
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");
        assertEquals("Epic sadface: Sorry, this user has been locked out.", loginPage.errorAlertText());
    }

    @DataProvider(name="loginOrPasswordData")
    public Object[][] loginOrPasswordData() {
        return new Object[][]{
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"}

        };
    }

    @Test(dataProvider = "loginOrPasswordData")
    public void checkErrorAuth(String username, String password, String exception){
        loginPage.open();
        loginPage.login(username,password);
        assertEquals(exception, loginPage.errorAlertText());
    }
}


