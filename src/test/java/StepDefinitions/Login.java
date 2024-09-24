package StepDefinitions;

import Pages.HomePage;
import Data.CsvHandler;
import Utilities.ElementNotFoundException;
import Utilities.JiraPolicy;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import Pages.LoginPage;
import Test.TestBase;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import static org.testng.Assert.fail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;


public class Login extends TestBase {
    private static final Logger logger = LoggerFactory.getLogger(Login.class);

    LoginPage loginobject = new LoginPage(driver);
    HomePage homeobject = new HomePage(driver);
    private CsvHandler csvReader;

    @JiraPolicy(logTicketReady = true)
    @Given("go to the website")
    public void login() {
        logger.info("Navigating to login page");
        loginobject.loadPage();

    }


    @JiraPolicy(logTicketReady = true)
    @When("enter username {string} and password {string}")
    public void loginUsernamePassword(String username,String password) throws InterruptedException {
        logger.info("Entering valid credentials");


            loginobject.text_Username(username);
            loginobject.text_Password(password);
            loginobject.click_Login();


    }

    @Then("try to open")
    public void try_to_open() {
        // Write code here that turns the phrase above into concrete actions

    }


    private Map<String, Object> userData;

    @JiraPolicy(logTicketReady = true)
    @Given("user data is loaded")
    public void loadUserData() throws IOException {
        // Load the JSON file from the resources directory
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("userData.json");

        if (inputStream == null) {
            throw new IOException("File not found: userData.json");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        userData = objectMapper.readValue(inputStream, Map.class);
    }

    @JiraPolicy(logTicketReady = true)
    @Given("user logs in using the data {string} and password {string}")
    public void userLogsInUsingTheData(String username,String password) {
        // Extract data from the Map and use it for login


        loginobject.text_Username((String) userData.get(username));
        loginobject.text_Password((String) userData.get(password));
        loginobject.click_Login();


        // Add logic to perform the login
    }

//    @Given("user data is loaded from CSV {string}")
//    public void loadUserDataFromCsv(String fileName) throws IOException {
//        csvReader = new CsvHandler(fileName);
//    }
//
//    @Given("user logs in using data where {string} is {string} and gets {string}")
//    public void userLogsInUsingDataWhereCondition(String searchColumn, String searchValue, String targetColumn) {
//        String data = csvReader.getData(searchColumn, searchValue, targetColumn);
//        loginobject.text_Username(searchValue);
//        loginobject.text_Password(data);
//        loginobject.click_Login();
//        System.out.println("Data from column " + targetColumn + " where " + searchColumn + " is " + searchValue + ": " + data);
//
//        // Add logic to use this data (e.g., login, form filling, etc.)
//    }

    @JiraPolicy(logTicketReady = true)
    @When("Switch to Iframe {string}")
    public void switchIframe(String iframe) throws InterruptedException {
        loginobject.switchIframe(iframe);
    }

    @JiraPolicy(logTicketReady = true)
    @Then("validate home page appeared")
    public void validateHomePageAppeared() {

        homeobject.getTitleText();
        Assert.assertNotNull(homeobject.getTitleText(), "Element should exist");

    }
}
