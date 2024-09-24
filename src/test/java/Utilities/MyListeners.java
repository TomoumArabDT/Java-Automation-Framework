//package Utilities;
//
//import Pages.BasePage;
//import com.aventstack.extentreports.MediaEntityBuilder;
//import com.aventstack.extentreports.Status;
//import net.rcarz.jiraclient.Attachment;
//import net.rcarz.jiraclient.Issue;
//import org.apache.commons.lang.exception.ExceptionUtils;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//
//
//import java.io.File;
//import java.util.Arrays;
//
//public class MyListeners implements ITestListener {
//
//    private static String getTestMethodName(ITestResult iTestResult) {
//        return iTestResult.getMethod().getConstructorOrMethod().getName();
//    }
//
//
//    @Override
//    public void onFinish(ITestContext arg0) {
////        System.out.println(arg0.getName());
//        System.out.println("I am in on Finish method ... " + arg0.getName());
//    }
//
//
//    @Override
//    public void onStart(ITestContext arg0) {
//        System.out.println("TEST CASES EXECUTION STARTED....... " + arg0.getName());
//    }
//
//    @Override
//    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
//        System.out.println("Percentage Status " + arg0.getStatus());
//        System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(arg0));
//    }
//
//
//
//
//    @Override
//    public void onTestFailure(ITestResult result) {
////        System.out.println(result.getStatus());
//        System.out.println("I am in on TestFailure, method " + getTestMethodName(result) + " Failed");
//
//        JiraPolicy jiraPolicy = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(JiraPolicy.class);
//        boolean isTicketReady = jiraPolicy.logTicketReady();
//
//        if (isTicketReady) {
//            //Raise Jira Ticket:
//            System.out.println("is Ticket ready for JIRA : " + isTicketReady);
//            JiraServiceProvider jiraSp = new JiraServiceProvider("https://arabdt.atlassian.net//",
//                    "",
//                    "",
//                    "NB - QC Team Activities");
//
//            String issueSummary = "Automation TestCase_" + result.getMethod().getConstructorOrMethod().getMethod().getName()
//                    + " got Failed due Assertion or an Exception";
//            String issueDescription = result.getThrowable().getMessage() + "\n";
//            issueDescription.concat(ExceptionUtils.getFullStackTrace(result.getThrowable()));
//
//            ///ScreenShot Code
//
//            //System.out.println("I am in onTestFailure method " + getTestMethodName(result) + " failed");
//
//            Object testClass = result.getInstance();
//            WebDriver wedriver = ((BasePage) testClass).getWebDriver();
//
//
//            //Taking Screenshot and Add to Report
//            String base64Screenshot = "data:image/png;base64,"
//                    + ((TakesScreenshot) wedriver).getScreenshotAs(OutputType.BASE64);
//
//
//            // Take base64Screenshot screenshot to be added in jira issue.
//            File file = ((TakesScreenshot) wedriver).getScreenshotAs(OutputType.FILE);
//
//            Issue issue = jiraSp.createJiraTicket("Bug", issueSummary, issueDescription, "Automation", file);
//
//        }
//
//    }
//
//
//}
