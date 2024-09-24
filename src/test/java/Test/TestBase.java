package Test;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.text.SimpleDateFormat;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;
import java.util.Date;


public class TestBase extends AbstractTestNGCucumberTests {

    public static WebDriver driver;



//	public static ChromeOptions chromeOption() {
//		String chrome = System.getProperty("user.dir")+"\\Chrome_Driver\\chromedriver.exe";
//		System.setProperty("webdriver.chrome.driver", chrome);
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--remote-allow-origins=*");
//		return options;
//	}

    @BeforeMethod
    @Parameters("browser")
    public void setup(@Optional("chrome") String browser) {
        //String chrome = System.getProperty("user.dir")+"\\Chrome_Driver\\ChromeDriver-104.exe";
//        String chrome = System.getProperty("user.dir")+"\\Chrome_Driver\\chromedriver.exe";
//        System.setProperty("webdriver.chrome.driver", chrome);
        //try new setup
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--remote-allow-origins=*");
//        //options.addArguments("--headless");
//        driver =  new ChromeDriver(options);
//        driver.manage().window().maximize();

        String reportPath = "target/cucumberReports/" + browser + "-cucumber-pretty.html";
        System.setProperty("cucumber.plugin", "html:" + reportPath);

        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
        }
    }

//	@AfterMethod
//	public void teardown() {
//		//driver.close();
//		driver.quit();
//	}

    @AfterMethod
    public void tearDown() {
        if (ITestResult.FAILURE == result.getStatus()) {
            takeScreenshot(result.getName());
        }
        // Quit the driver
        if (driver != null) {
            driver.quit();
        }
    }

    public void takeScreenshot(String testName) {
        // Format the screenshot file name
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String fileName = testName + "_" + timeStamp + ".png";
        String filePath = System.getProperty("user.dir") + "/screenshots/" + fileName;

        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File(filePath));
            System.out.println("Screenshot taken: " + filePath);
        } catch (WebDriverException | IOException e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
        }
    }

}

