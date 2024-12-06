package tests;


import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.ProductsPage;
import utils.AllureUtils;
import utils.PropertyReader;


import java.util.concurrent.TimeUnit;

public class BaseTest {
    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    String user;
    String password;

    @Parameters({"browser"})
    @BeforeMethod
    @Description("Открытие")
    public void setup_setting(@Optional("chrome") String browser, ITestContext context) {
        System.out.println("Browser parameter: " + browser);
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
        }

        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        context.setAttribute("driver", driver);
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);

        System.setProperty("BASE_URL", PropertyReader.getProperty("sauce.url"));
        user = PropertyReader.getProperty("sauce.user");
        password = PropertyReader.getProperty("sauce.password");

    }


    @AfterMethod
    @Description("Закрытие")
    public void close(ITestResult result) {
          if (ITestResult.FAILURE == result.getStatus()) {
                AllureUtils.takeScreenshot(driver);
            }
        driver.quit();
    }
}
