package Runner;



import io.cucumber.java.BeforeAll;
import io.cucumber.testng.CucumberOptions;
import Test.TestBase;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


@CucumberOptions(features = "src/test/resources", glue = {"StepDefinitions"},
        plugin = {"pretty","html:target/cucumber-html-report",
                "html:target/cucumberReports/cucumber-pretty.html",
                "json:target/cucumberReports/cucumber-TestReport.json",
                "rerun:target/cucumberReports/rerun.txt",
                "junit:target/cucumberReports/cukes.xml",},monochrome = true
        ,tags = "@tag0"
)

public class TestRunner extends TestBase{





}
