package stepDefinitions.login;
import java.time.Duration;
import stepDefinitions.order.OrderSteps;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {
	
	static WebDriver driver;
	static WebDriverWait wait;
	
	@BeforeAll
	public static void setup() {
		String browser = "chrome"; 
	    if (browser.equalsIgnoreCase("chrome"))
	        driver = new ChromeDriver();
	    else if (browser.equalsIgnoreCase("firefox"))
	        driver = new FirefoxDriver();
	    
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
	}
	@AfterAll
	public static void teardown(){
		driver.quit();
	}
	
	@Given("I navigate to page {string}")
	public void i_navigate_to_page(String Url) {
	    driver.get(Url);
	}
	@Then("I verify that the home page is visible successfully")
	public void i_verify_that_the_home_page_is_visible_successfully() {
		String pageTitle = driver.getTitle();
	    System.out.println(pageTitle);
	}
	@When("I click on Signup Login button")
	public void i_click_on_signup_login_button() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[4]/a"))).click();
	}
	@Then("I verify {string} is visible")
	public void i_verify_is_visible(String expected_Signup) {
		WebElement actual_Signup = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/h2"));
		if(actual_Signup.getText().trim().equals(expected_Signup)) {
			System.out.println("SignUp page is visible successfully.");
		} else {
			System.out.println("SignUp Page is NOT visible.");
		}
	}
	
	// Scenario 1
	
	@Then("I enter name and email address")
	public void i_enter_name_and_email_address() {
		driver.findElement(By.name("name")).sendKeys(OrderSteps.getData("sheet1",1,0));
	    driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/input[3]")).sendKeys(OrderSteps.getData("sheet1",1,1));
	}
	@When("I click Signup button to signup")
	public void i_click_signup_button_to_signup() {
		driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/button")).click();
	}
	@Then("I verify that {string} is visible")
	public void i_verify_that_is_visible(String expected_Title) {
		WebElement actual_Title = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/div/h2"));
		if(actual_Title.getText().trim().equals(expected_Title)) {
			System.out.println("Enter Account Information Page is visible successfully.");
		} else {
			System.out.println("Enter Account Information Page is NOT visible.");
		}
	}
	@Then("I fill in the account details")
	public void i_fill_in_the_account_details() {
		WebElement genderRadio = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_gender1")));
	    genderRadio.click();
//		driver.findElement(By.id("id_gender1")).click();
	    driver.findElement(By.id("password")).sendKeys(OrderSteps.getData("sheet1",1,2));

	    new Select(driver.findElement(By.id("days"))).selectByIndex(5);
	    new Select(driver.findElement(By.id("months"))).selectByIndex(5);
	    new Select(driver.findElement(By.id("years"))).selectByIndex(5);

	    driver.findElement(By.id("first_name")).sendKeys(OrderSteps.getData("sheet1",1,3));
	    driver.findElement(By.id("last_name")).sendKeys(OrderSteps.getData("sheet1",1,4));
	    driver.findElement(By.id("address1")).sendKeys(OrderSteps.getData("sheet1",1,5));
	    driver.findElement(By.id("state")).sendKeys(OrderSteps.getData("sheet1",1,6));
	    driver.findElement(By.id("city")).sendKeys(OrderSteps.getData("sheet1",1,7));
	    driver.findElement(By.id("zipcode")).sendKeys(OrderSteps.getData("sheet1",1,8));
	    driver.findElement(By.id("mobile_number")).sendKeys(OrderSteps.getData("sheet1",1,9));
	}
	@Then("I click Create Account button")
	public void i_click_create_account_button() {
		WebElement element = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/div/form/button"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element); 
		element.click();
	}
	@Then("I check that {string} is visible")
	public void i_check_that_is_visible(String ex_title) {
		WebElement act_Title = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/h2"));
		if(act_Title.getText().trim().equals(ex_title)) {
			System.out.println("Conformed the account creation.");
		} else {
			System.out.println("Unable to conform the account creation.");
		} 
	}
	@When("I click Continue")
	public void i_click_continue() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement elements = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"form\"]/div/div/div/div/a")));
		elements.click();
	}
	@Then("the user should see the username {string}")
	public void the_user_should_see_the_username(String expectedMessage) {
		try {
			WebElement messageElement = driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[10]/a/b"));
			String actualMessage = messageElement.getText().trim();
			System.out.println("Actual Message: " + actualMessage);
			Assert.assertTrue(actualMessage.equalsIgnoreCase(expectedMessage)," Expected message not displayed. Found: " + actualMessage);
		} catch (Exception e) {
			System.out.println("Exception occurred: " + e.getMessage());
		}
	}
	@When("I click Delete Account button")
	public void i_click_delete_account_button() {
		driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[5]/a")).click();
	}
	@Then("I verify that {string} is available")
	public void i_verify_that_is_available(String delete_title) {
		WebElement Delete_Title = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/h2"));
		if(Delete_Title.getText().trim().equals(delete_title)) {
			System.out.println("Conformed the account deletion.");
		} else {
			System.out.println("Unable to conform the account deletion.");
		} 
	}
	@Then("I click Continue button")
	public void i_click_continue_button() {
		driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/div/a")).click();
	}

	// Scenario 2
	
	@When("I enter valid email address and password")
	public void i_enter_valid_email_address_and_password() {
		driver.findElement(By.name("email")).sendKeys(OrderSteps.getData("sheet1",1,15));
	    driver.findElement(By.name("password")).sendKeys(OrderSteps.getData("sheet1",1,16));
	    }
	@Then("click login")
	public void click_login() {
	   WebElement Login = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/form/button"));
	   Login.click();
	}
	@Then("verify to see message username {string}")
	public void verify_to_see_message_username(String expectedMessage) {
		try {
			WebElement messageElement = driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[10]/a/b"));
			String actualMessage = messageElement.getText().trim();
			System.out.println("Actual Message: " + actualMessage);
			Assert.assertTrue(actualMessage.equalsIgnoreCase(expectedMessage)," Expected message not displayed. Found: " + actualMessage);
		} catch (Exception e) {
			System.out.println("Exception occurred: " + e.getMessage());
		}
	}

	
	// Scenario 3
	
	
	@When("I enter invalid email address and password")
	public void i_enter_invalid_email_address_and_password() {
		driver.findElement(By.name("email")).sendKeys(OrderSteps.getData("sheet1",1,1));
	    driver.findElement(By.name("password")).sendKeys(OrderSteps.getData("sheet1",1,16));
	}
	@Then("click login button")
	public void click_login_button() {
		WebElement Login = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/form/button"));
		Login.click();
	}
	@Then("verify error")
	public void verify_error() {
		try {
			String expectederror = "Your email or password is incorrect!";
			WebElement errorElement = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/form/p"));
			String actualerror = errorElement.getText().trim();
			System.out.println("Actual error: " + actualerror);
			Assert.assertTrue(actualerror.equalsIgnoreCase(expectederror),
					" Expected message not displayed. Found: " + actualerror);
		} catch (Exception e) {
			System.out.println("Exception occurred: " + e.getMessage());
		} 
	}
	
	
	// Scenario 4
	
	
	@When("enter email and password")
	public void enter_email_and_password() {
		driver.findElement(By.name("email")).sendKeys(OrderSteps.getData("sheet1",1,15));
	    driver.findElement(By.name("password")).sendKeys(OrderSteps.getData("sheet1",1,16));
	}
	@Then("i click login button")
	public void i_click_login_button() {
		driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/form/button")).click();
	}
	@Then("verify message username {string}")
	public void verify_message_username(String expectedMessage) {
		try {
			WebElement messageElement = driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[10]/a/b"));
			String actualMessage = messageElement.getText().trim();
			System.out.println("Actual Message: " + actualMessage);
			Assert.assertTrue(actualMessage.equalsIgnoreCase(expectedMessage)," Expected message not displayed. Found: " + actualMessage);
		} catch (Exception e) {
			System.out.println("Exception occurred: " + e.getMessage());
		}  
	}
	@When("click logout")
	public void click_logout() {
		driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[4]/a"));
	}
	@Then("verify user is in login page")
	public void verify_user_is_in_login_page() {
		driver.getTitle();
	}
	
	// scenario 5
		
	@When("i register")
	public void i_register() {
		driver.findElement(By.name("name")).sendKeys(OrderSteps.getData("sheet1",1,0));
	    driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/input[3]")).sendKeys(OrderSteps.getData("sheet1",1,15));
	}
	@Then("click signup")
	public void click_signup() {
		driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/button")).click();
	}
	@Then("verify error message")
	public void verify_error_message() {
		boolean error = driver.findElements(By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/p")).size()>0;
	    System.out.println(error ? "Email Address already exist!" : "'Email Address already exist!' message is NOT visible.");   
	}
	
}