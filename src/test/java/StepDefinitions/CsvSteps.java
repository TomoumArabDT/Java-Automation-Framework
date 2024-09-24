package StepDefinitions;

import Data.CsvHandler;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvSteps {

    private CsvHandler csvHandler = new CsvHandler();
    private List<Map<String, String>> csvData = new ArrayList<>();

    @When("I add the following data to CSV:")
    public void addDataToCsv(Map<String, String> data) {
        // Add the data as a new row in the csvData list
        csvData.add(new HashMap<>(data));
    }

    @When("I save the data to CSV file {string}")
    public void saveDataToCsv(String fileName) throws IOException {
        csvHandler.writeCsvToFile("src/test/java/Data/" + fileName, csvData);
    }

    @When("I read the CSV file {string}")
    public void readCsvFile(String fileName) throws IOException {
        csvData = csvHandler.readCsvFromFile("src/test/java/Data/" + fileName);

    }

    @Then("the value in row {int} and column {string} should be {string}")
    public void valueShouldBe(int rowIndex, String columnName, String expectedValue) {
        String actualValue = csvData.get(rowIndex).get(columnName);
        if (!expectedValue.equals(actualValue)) {
            throw new AssertionError("Expected value for column '" + columnName + "' in row '" + rowIndex + "' was '" + expectedValue + "', but found '" + actualValue + "'");
        }
    }
}
