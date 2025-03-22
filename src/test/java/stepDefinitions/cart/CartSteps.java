package stepDefinitions.cart;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import stepDefinitions.order.OrderSteps;

public class CartSteps {

	static WebDriver driver;
	static WebDriverWait wait;
	static JavascriptExecutor js;

	@BeforeAll()
	public static void setup() {
		String browser = "chrome"; 
	    if (browser.equalsIgnoreCase("chrome"))
	        driver = new ChromeDriver();
	    else if (browser.equalsIgnoreCase("firefox"))
	        driver = new FirefoxDriver();
	    
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		js = (JavascriptExecutor) driver;
	}

	@AfterAll()
	public static void tearup() {
		driver.quit();
	}

	@Given("I navigate to {string} website")
	public void i_navigate_to_website(String url) {
		driver.get(url);
	}


	// Scenario 20

	@When("click products")
	public void click_products() {
		driver.findElement(By.partialLinkText(" Products")).click();
	}
	@Then("veify the navigated page")
	public void veify_the_navigated_page() {
		String actualTitle = driver.getTitle();
		String expectedTitle = "Automation Exercise - All Products";
		Assert.assertTrue(actualTitle.equalsIgnoreCase(expectedTitle), "Displayed Title: "+actualTitle);
	}
	@When("search for product and click search")
	public void search_for_product_and_click_search() {
		driver.findElement(By.id("search_product")).sendKeys("shirt");
		driver.findElement(By.id("submit_search")).click();
	}
	@Then("verify the page searched products")
	public void verify_the_page_searched_products() {
		String searchProducts = driver.findElement(By.xpath("/html/body/section[2]/div/div/div[2]/div/h2")).getText().trim();
		System.out.println(searchProducts);
	}
	@Then("verify all relate products are visible")
	public void verify_all_relate_products_are_visible() {
		List<WebElement> productList = driver.findElements(By.xpath("//div[@class='product-list']//h2"));
		for (WebElement product : productList) {
			Assert.assertTrue(product.getText().toLowerCase().contains("shirt"), "Product does not match the search criteria: " + product.getText());
		}
	}
	@Then("add those products to cart")
	public void add_those_products_to_cart() {
		for (WebElement addToCartButton : driver.findElements(By.className("add-to-cart"))) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCartButton);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartButton);
			WebElement continueShopping = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Continue Shopping')]")));
			continueShopping.click();
		}
	}
	@Then("click cart and verify products are visible")
	public void click_cart_and_verify_products_are_visible() {
		driver.findElement(By.linkText("Cart")).click();
		Assert.assertFalse(driver.findElements(By.className("cart_product")).isEmpty());
	}
	@When("click login and enter login credentials")
	public void click_login_and_enter_login_credentials() {
		driver.findElement(By.partialLinkText("Login")).click();
		 driver.findElement(By.name("email")).sendKeys(OrderSteps.getData("sheet1",1,15));
		    driver.findElement(By.name("password")).sendKeys(OrderSteps.getData("sheet1",1,16));
		driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/form/button")).click();
	}
	@Then("go to cart")
	public void go_to_cart() {
		driver.findElement(By.partialLinkText("Cart")).click();
	}
	@Then("verify all products added to cart are visible")
	public void verify_all_products_added_to_cart_are_visible() {
		Assert.assertFalse(driver.findElements(By.className("cart_product")).isEmpty());
	}
	@Then("remove all products from cart")
	public void remove_all_products_from_cart(){
		for (WebElement remove : driver.findElements(By.className("cart_quantity_delete"))) {
		    try {
		        wait.until(ExpectedConditions.elementToBeClickable(remove)).click();
		    } catch (ElementClickInterceptedException e) {
		        JavascriptExecutor js = (JavascriptExecutor) driver;
		        js.executeScript("arguments[0].click();", remove);
		    }
		}
	}
	@Then("verify message {string}")
	public void verify_message(String expectedMessage) {
		WebElement emptyCartMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("empty_cart")));
		String actualMessage = emptyCartMsg.getText();
		System.out.println("Actual Message: " + actualMessage);
		Assert.assertTrue(actualMessage.trim().contains(expectedMessage), "Expected message not found!");
	}


	
	// Scenario 21
	
	@When("i click products button")
	public void i_click_products_button() {
		driver.findElement(By.partialLinkText(" Products")).click();
	}
	@Then("veify the navigated to all products page")
	public void veify_the_navigated_to_all_products_page() {
		String actualTitle = driver.getTitle();
		String expectedTitle = "Automation Exercise - All Products";
		Assert.assertTrue(actualTitle.equalsIgnoreCase(expectedTitle), "Displayed Title: "+actualTitle);
	}
	@Then("click on view products")
	public void click_on_view_products() {
		WebElement viewProduct = driver.findElement(By.xpath("/html/body/section[2]/div/div/div[2]/div[1]/div[2]/div/div[2]/ul/li/a"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", viewProduct);
		viewProduct.click();

	}
	@When("verify {string} is visible")
	public void verify_is_visible(String expectedreviewmsg) {
	    String actualreviewmsg = driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div[3]/div[1]/ul/li/a")).getText();
	    Assert.assertTrue(actualreviewmsg.equalsIgnoreCase(expectedreviewmsg), "Displayed Title: "+actualreviewmsg);
	}
	@Then("enter name email and review")
	public void enter_name_email_and_review() {
	    driver.findElement(By.id("name")).sendKeys(OrderSteps.getData("sheet1",1,0));
	    driver.findElement(By.id("email")).sendKeys(OrderSteps.getData("sheet1",1,15));
	    driver.findElement(By.id("review")).sendKeys(OrderSteps.getData("sheet1",1,17));
	}
	@Then("click submit")
	public void click_submit() {
		if ((Boolean) js.executeScript("return document.getElementById('aswift_3_host') !== null;")) {
		    js.executeScript("document.getElementById('aswift_3_host').remove();");
		}
		WebElement submit = driver.findElement(By.id("scrollUp"));
		js.executeScript("arguments[0].click();", submit);
//		driver.findElement(By.id("button-review")).click();
	}
	@Then("verify review message")
	public void verify_review_message() {
		String actualText = driver.findElement(By.xpath("//*[@id='review-section']//div/div/span")).getText();
		System.out.println(actualText);

	}

	
	
	// Scenario 22
	
	@When("scroll to bottom")
	public void scroll_to_bottom() {
		JavascriptExecutor j = (JavascriptExecutor)driver;
		j.executeScript("window.scrollBy(0,7500)");
	}
	@Then("verify {string} on bottom of page")
	public void verify_on_bottom_of_page(String expectedTitle) {
		String actualTitle = driver.findElement(By.xpath("/html/body/section[2]/div/div/div[2]/div[2]/h2")).getText();
	    Assert.assertTrue(actualTitle.equalsIgnoreCase(expectedTitle), "Displayed Title: "+actualTitle);
	}
	@Then("add to cart recommended items")
	public void add_to_cart_recommended_items(){
	    driver.findElement(By.xpath("//*[@id=\"recommended-item-carousel\"]/div/div[2]/div[1]/div/div/div/a")).click();
	}
	@Then("click on view cart button")
	public void click_on_view_cart_button() {
		WebElement viewCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"cartModal\"]/div/div/div[2]/p[2]/a")));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewCartButton);
	}
	@Then("verify product displayed in cart")
	public void verify_product_displayed_in_cart() {
		Assert.assertFalse(driver.findElements(By.className("cart_product")).isEmpty());
	}
	
}
