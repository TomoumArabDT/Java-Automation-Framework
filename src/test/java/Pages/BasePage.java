package Pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Random;

import Utilities.ElementNotFoundException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The `BasePage` class serves as the base for all other page classes.
 * It provides common methods for interacting with web elements using Selenium WebDriver.
 * This class includes methods for clicking elements, handling dropdowns, generating random data,
 * switching iframes, and more.
 */
public class BasePage {

    // Initializing the logger for this class
    private static final Logger logger = LoggerFactory.getLogger(BasePage.class);

    private String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String numbers = "123456789";
    private Random rand = new Random();
    public WebDriver driver;
    public String page_url;
    public JavascriptExecutor js;
    private Select select;

    /**
     * Constructor that initializes the WebDriver and sets up the JavascriptExecutor.
     *
     * @param driver WebDriver instance to be used for interacting with web elements.
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        js = (JavascriptExecutor) driver;
        logger.info("BasePage initialized with WebDriver");
    }


    public WebDriver getWebDriver() {
        return driver;
    }

    /**
     * Loads the page using the URL stored in the `page_url` field.
     */
    public void loadPage() {
        try {
            logger.info("Loading page: {}", page_url);
            driver.get(page_url);
        } catch (Exception e) {
            logger.error("Failed to load the page: {}", page_url, e);
            throw new RuntimeException("Failed to load the page: " + page_url, e);
        }
    }

    /**
     * Returns the current page URL.
     *
     * @return String representing the page URL.
     */
    public String getPageUrl() {
        try {
            logger.info("Getting the page URL");
            return page_url;
        } catch (Exception e) {
            logger.error("Failed to get the page URL", e);
            throw new RuntimeException("Failed to get the page URL", e);
        }
    }

    /**
     * Clicks on a specified element identified by the given locator.
     *
     * @param element Locator of the element to be clicked.
     */
    protected void clickElement(By element) {
        try {
            logger.info("Clicking element: {}", element);
            WebElement webElement = waitForElement(element);
            webElement.click();
        } catch (NoSuchElementException e) {
            logger.error("Element not found for clicking: {}", element, e);
            throw new ElementNotFoundException("Element not found for clicking", e);
        }
    }

    /**
     * Checks if a specified element is displayed.
     *
     * @param element Locator of the element to check for visibility.
     * @return true if the element is displayed, false otherwise.
     */
    protected boolean isDisplayed(By element) {
        try {
            logger.info("Checking if element is displayed: {}", element);
            WebElement webElement = waitForElement(element);
            return webElement.isDisplayed();
        } catch (NoSuchElementException e) {
            logger.error("Element not found for checking visibility: {}", element, e);
            throw new ElementNotFoundException("Element not found for checking visibility", e);
        }
    }

    /**
     * Returns a Select object representing a dropdown menu for the specified element.
     *
     * @param element Locator of the dropdown element.
     * @return Select object for interacting with the dropdown menu.
     */
    protected Select dropdown(By element) {
        try {
            logger.info("Finding dropdown element: {}", element);
            WebElement webElement = waitForElement(element);
            return new Select(webElement);
        } catch (NoSuchElementException e) {
            logger.error("Dropdown element not found: {}", element, e);
            throw new ElementNotFoundException("Dropdown element not found", e);
        }
    }

    /**
     * Selects a value from a dropdown menu.
     *
     * @param dropdown Select object representing the dropdown menu.
     * @param value String value to be selected in the dropdown.
     */
    protected void dropdownvalue(Select dropdown, String value) {
        try {
            logger.info("Selecting value '{}' from dropdown", value);
            dropdown.selectByValue(value);
        } catch (NoSuchElementException e) {
            logger.error("Value '{}' not found in dropdown", value, e);
            throw new ElementNotFoundException("Value not found in dropdown", e);
        }
    }

    /**
     * Checks if a specified element is selected.
     *
     * @param element Locator of the element to check for selection.
     * @return true if the element is selected, false otherwise.
     */
    protected boolean isSelected(By element) {
        try {
            logger.info("Checking if element is selected: {}", element);
            return driver.findElement(element).isSelected();
        } catch (NoSuchElementException e) {
            logger.error("Element not found for checking selection: {}", element, e);
            throw new ElementNotFoundException("Element not found for checking selection", e);
        }
    }

    /**
     * Sends text to a specified element.
     *
     * @param element Locator of the element where text will be sent.
     * @param text Text to be sent to the element.
     */
    protected void sendtext(By element, String text) {
        try {
            logger.info("Sending text '{}' to element: {}", text, element);
            WebElement webElement = waitForElement(element);
            webElement.sendKeys(text);
        } catch (NoSuchElementException e) {
            logger.error("Element not found for sending text: {}", element, e);
            throw new ElementNotFoundException("Element not found for sending text", e);
        }
    }

    /**
     * Switches to a specific window based on the window index.
     *
     * @param window_index Index of the window to switch to.
     * @throws InterruptedException if the thread is interrupted while waiting.
     */
    public void windowSwitch(int window_index) throws InterruptedException {
        try {
            logger.info("Switching to window index: {}", window_index);
            ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tab.get(window_index));
        } catch (Exception e) {
            logger.error("Failed to switch to window index: {}", window_index, e);
            throw new RuntimeException("Failed to switch to window index: " + window_index, e);
        }
    }

    /**
     * Performs a mouse hover action on a specified element.
     *
     * @param e WebElement to hover over.
     */
    public void mouse_hover(WebElement e) {
        try {
            logger.info("Performing mouse hover on element");
            Actions act = new Actions(driver);
            act.moveToElement(e).perform();
        } catch (Exception ex) {
            logger.error("Failed to perform mouse hover", ex);
            throw new RuntimeException("Failed to perform mouse hover", ex);
        }
    }

    /**
     * Sets an implicit wait of 10 seconds for finding elements.
     */
    public void implicitlyWait() {
        try {
            logger.info("Setting implicit wait to 10 seconds");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        } catch (Exception e) {
            logger.error("Failed to set implicit wait", e);
            throw new RuntimeException("Failed to set implicit wait", e);
        }
    }

    /**
     * Switches to a specified iframe by name or ID.
     *
     * @param iframe Name or ID of the iframe to switch to.
     */
    public void switchIframe(String iframe) {
        try {
            logger.info("Switching to iframe: {}", iframe);
            driver.switchTo().frame(iframe);
        } catch (NoSuchFrameException e) {
            logger.error("Iframe not found: {}", iframe, e);
            throw new RuntimeException("Iframe not found: " + iframe, e);
        }
    }

    /**
     * Switches back to the main content from an iframe.
     */
    public void switchBackIframe() {
        try {
            logger.info("Switching back to default content");
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            logger.error("Failed to switch back to default content", e);
            throw new RuntimeException("Failed to switch back to default content", e);
        }
    }

    /**
     * Gets text from a specified element.
     *
     * @param element Locator of the element to get text from.
     * @return String text from the element.
     */
    protected String getText(By element) {
        try {
            logger.info("Getting text from element: {}", element);
            WebElement webElement = waitForElement(element);
            return webElement.getText();
        } catch (NoSuchElementException e) {
            logger.error("Element not found for getting text: {}", element, e);
            throw new ElementNotFoundException("Element not found for getting text", e);
        }
    }

    /**
     * Clears text in a specified element.
     *
     * @param element Locator of the element to clear text from.
     */
    protected void clearText(By element) {
        try {
            logger.info("Clearing text in element: {}", element);
            WebElement webElement = waitForElement(element);
            webElement.clear();
        } catch (NoSuchElementException e) {
            logger.error("Element not found for clearing text: {}", element, e);
            throw new ElementNotFoundException("Element not found for clearing text", e);
        }
    }

    /**
     * Returns a WebElement identified by the specified locator.
     *
     * @param element Locator of the element to find.
     * @return WebElement found using the locator.
     */
    protected WebElement onlyElement(By element) {
        try {
            logger.info("Finding element: {}", element);
            return waitForElement(element);
        } catch (NoSuchElementException e) {
            logger.error("Element not found: {}", element, e);
            throw new ElementNotFoundException("Element not found", e);
        }
    }

    /**
     * Pauses execution for 1 second.
     *
     * @param milesec to set the duration of the sleep
     * @throws InterruptedException if the thread is interrupted while sleeping.
     */
    public void sleepWait(int milesec) throws InterruptedException {
        try {
            logger.info("Pausing execution for 1 second");
            Thread.sleep(milesec);
        } catch (InterruptedException e) {
            logger.error("Sleep wait interrupted", e);
            throw new RuntimeException("Sleep wait interrupted", e);
        }
    }

    /**
     * Generates a random name using letters from the `lexicon` field.
     *
     * @return String representing a randomly generated name.
     */
    public String randomName() {
        try {
            logger.info("Generating random name");
            StringBuilder builder = new StringBuilder();
            while (builder.toString().length() == 0) {
                int length = rand.nextInt(7) + 7;
                for (int i = 0; i < length; i++) {
                    builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
                }
            }
            return builder.toString();
        } catch (Exception e) {
            logger.error("Failed to generate random name", e);
            throw new RuntimeException("Failed to generate random name", e);
        }
    }

    /**
     * Generates a random number using digits from the `numbers` field.
     *
     * @return String representing a randomly generated number.
     */
    public String randomNumber() {
        try {
            logger.info("Generating random number");
            StringBuilder builder = new StringBuilder();
            while (builder.toString().length() == 0) {
                int length = rand.nextInt(7) + 7;
                for (int i = 0; i < length; i++) {
                    builder.append(numbers.charAt(rand.nextInt(numbers.length())));
                }
            }
            return builder.toString();
        } catch (Exception e) {
            logger.error("Failed to generate random number", e);
            throw new RuntimeException("Failed to generate random number", e);
        }
    }

    /**
     * Scrolls down the page by a specified number of pixels using JavaScript.
     */
    public void scrolldown() {
        try {
            logger.info("Scrolling down the page");
            js.executeScript("window.scrollBy(0,3550)", "");
        } catch (Exception e) {
            logger.error("Failed to scroll down", e);
            throw new RuntimeException("Failed to scroll down", e);
        }
    }

    /**
     * Scrolls the page to bring a specified element into view using JavaScript.
     *
     * @param element WebElement to scroll into view.
     */
    public void scrollIntoViewElement(WebElement element) {
        try {
            logger.info("Scrolling into view of element");
            js.executeScript("arguments[0].scrollIntoView();", element);
        } catch (Exception e) {
            logger.error("Failed to scroll into view", e);
            throw new RuntimeException("Failed to scroll into view", e);
        }
    }

    /**
     * Uploads a file to a specified element by sending the file path to the element.
     *
     * @param element Locator of the element to send the file path to.
     * @param filename Name of the file to be uploaded.
     */
    protected void uploadFile(By element, String filename) {
        try {
            String filepath = System.getProperty("user.dir") + "\\upload\\" + filename;
            logger.info("Uploading file: {}", filepath);
            sendtext(element, filepath);
        } catch (NoSuchElementException e) {
            logger.error("Element not found for file upload: {}", element, e);
            throw new ElementNotFoundException("Element not found for file upload", e);
        }
    }

    private WebElement waitForElement(By locator) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30)) // Maximum wait time
                .pollingEvery(Duration.ofSeconds(2)) // Check every 2 seconds
                .ignoring(NoSuchElementException.class); // Ignore this exception during polling

        return wait.until(driver -> driver.findElement(locator));
    }
}
