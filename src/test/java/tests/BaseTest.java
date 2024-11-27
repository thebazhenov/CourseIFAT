package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;


public class BaseTest {
    WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod
    public void setup_setting() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);

        loginPage = new LoginPage(driver);
    }

    @AfterMethod
    public void close() {
        driver.quit();
    }
}
