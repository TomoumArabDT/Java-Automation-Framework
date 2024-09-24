package Pages;

import Utilities.ElementNotFoundException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage{

    private By title_text = By.className("app_logo");

    /**
     * Constructor that initializes the WebDriver and sets up the JavascriptExecutor.
     *
     * @param driver WebDriver instance to be used for interacting with web elements.
     */
    public HomePage(WebDriver driver) {
        super(driver);
    }


    public WebElement getTitleText() {
        try {
            return onlyElement(title_text);
        } catch (NoSuchElementException e) {
            throw new ElementNotFoundException("Title page field not found on the home page", e);
        }


    }

}
