package StepDefinitions;
import Data.JsonHandler;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonSteps {

    private JsonHandler jsonHandler = new JsonHandler();
    private Map<String, Object> jsonData = new HashMap<>();

    @When("I add the following data to JSON:")
    public void addDataToJson(Map<String, String> data) {
        // Store each key-value pair in the jsonData map
        jsonData.putAll(data);
    }

    @When("I save the data to JSON file {string}")
    public void saveDataToJson(String fileName) throws IOException {
        jsonHandler.writeJsonToFile("src/test/java/Data/" + fileName, jsonData);
    }

    @When("I read the JSON file {string}")
    public void readJsonFile(String fileName) throws IOException {
        jsonData = jsonHandler.readJsonFromFile("src/test/java/Data/" + fileName);
    }

    @Then("the value of {string} should be {string}")
    public void valueShouldBe(String key, String expectedValue) {
        String actualValue = jsonData.get(key).toString();
        if (!expectedValue.equals(actualValue)) {
            throw new AssertionError("Expected value for key '" + key + "' was '" + expectedValue + "', but found '" + actualValue + "'");
        }
    }
}

