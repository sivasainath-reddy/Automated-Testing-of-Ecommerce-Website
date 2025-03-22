package runner;

import org.testng.annotations.Test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = 
{
//        "src/test/resources/features/login",
//        "src/test/resources/features/functionalities",
    	"src/test/resources/features/order",
//        "src/test/resources/features/cart",
//        "src/test/resources/features/purchase"
    },
    glue = {
//    		"stepDefinitions.login",
//    		"stepDefinitions.functional",
    		"stepDefinitions.order",
//    		"stepDefinitions.cart",
//    		"stepDefinitions.purchase",
    		},
    		plugin = {"pretty", "json:target/cucumber-reports-json/Cucumber.json"}, // Generate JSON report
//    plugin = {"pretty", "html:target/cucumber-reports.html"},
    monochrome = true
)
@Test
public class TestRunner extends AbstractTestNGCucumberTests {
}
