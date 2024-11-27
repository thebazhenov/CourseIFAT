package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage{

    private final By USERNAME_INPUT = By.xpath("//input[@placeholder='Username']");
    private final By PASSWORD_INPUT = By.xpath("//input[@placeholder='Password']");
    private final By LOGIN_BUTTON = By.xpath("//input[@id='login-button']");
    private final By ERROR_WINDOW = By.xpath("//h3[@data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open(){
        driver.get(BASE_URL);
    }

    public void login(String login, String password){
        driver.findElement(USERNAME_INPUT).sendKeys(login);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).submit();
    }

    public String errorAlertText(){
        WebElement alertText = driver.findElement(ERROR_WINDOW);
        return alertText.getText();
    }
}