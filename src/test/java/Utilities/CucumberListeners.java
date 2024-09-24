package Utilities;

import Pages.BasePage;
import com.aventstack.extentreports.MediaEntityBuilder;
import net.rcarz.jiraclient.Issue;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import java.io.File;

public class CucumberListeners {

    // This method runs before any scenario starts
    @Before
    public void onStart(Scenario scenario) {
        System.out.println("SCENARIO EXECUTION STARTED: " + scenario.getName());
    }

    // This method runs after every scenario
    @After
    public void onFinish(Scenario scenario) {
        System.out.println("SCENARIO EXECUTION FINISHED: " + scenario.getName());

        if (scenario.isFailed()) {
            System.out.println("SCENARIO FAILED: " + scenario.getName());
            handleFailure(scenario);
        }
    }
    private void handleFailure(Scenario scenario) {
        // Iterate over the methods in step definition classes and check if @JiraPolicy is present
        boolean isTicketReady = false;

        try {
            // Assuming you have step definition classes you want to check
            for (Object stepClass : scenario.getSourceTagNames()) {
                Class<?> clazz = stepClass.getClass();
                for (java.lang.reflect.Method method : clazz.getDeclaredMethods()) {
                    if (method.isAnnotationPresent(JiraPolicy.class)) {
                        JiraPolicy jiraPolicy = method.getAnnotation(JiraPolicy.class);
                        if (jiraPolicy.logTicketReady()) {
                            isTicketReady = true;
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (isTicketReady) {
            // Raise Jira Ticket
            JiraServiceProvider jiraSp = new JiraServiceProvider("https://your-jira-url",
                    "your-email",
                    "your-api-token",
                    "project-key");

            String issueSummary = "Automation TestCase_" + scenario.getName() + " Failed";
            String issueDescription = "Scenario failed due to an exception or assertion failure.";

//            Throwable throwable = scenario.getStatus().isFailed() ? scenario.getStatus().getError() : null;
//            if (throwable != null) {
//                issueDescription += "\n" + ExceptionUtils.getFullStackTrace(throwable);
//            }
//
//            WebDriver driver = ((BasePage) scenario.getSource()).getWebDriver();
//            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            Issue issue = jiraSp.createJiraTicket("Bug", issueSummary, issueDescription, "Automation");
        }
    }
    // Take screenshot after every step for reporting (optional)
//    @AfterStep
//    public void afterStep(Scenario scenario) {
//        if (scenario.isFailed()) {
//            // Capture screenshot if step failed
//            WebDriver driver = ((BasePage) scenario.getSource()).getWebDriver();
//            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//            scenario.attach(screenshot, "image/png", "Screenshot on Failure");
//        }
    }

