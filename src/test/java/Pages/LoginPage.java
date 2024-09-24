package Pages;

import Data.ConfigReader;
import Utilities.ElementNotFoundException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;


public class LoginPage extends BasePage {


    ConfigReader configReader = new ConfigReader();
    private By username_text = By.name("user-name");
    private By password_text = By.name("password");
    private By login_button = By.id("login-button");



    public LoginPage(WebDriver driver) {
        super(driver);

        this.page_url = configReader.getEnvironmentProperty("url");;
        // TODO Auto-generated constructor stub
    }



    public void text_Username(String username) {

            sendtext(username_text,username);
    }

    public void text_Password(String password) {

        sendtext(password_text,password);
    }

    public void click_Login() {
            clickElement(login_button);
    }




}
