package stepDefinitions.functional;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import stepDefinitions.order.OrderSteps;

public class FunctionalSteps {
	
	static WebDriver driver ;
	static WebDriverWait wait; 
	static Actions actions;
	
	@BeforeAll()
	public static void setup() {
		String browser = "chrome"; 
	    if (browser.equalsIgnoreCase("chrome"))
	        driver = new ChromeDriver();
	    else if (browser.equalsIgnoreCase("firefox"))
	        driver = new FirefoxDriver();
	    
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
		actions = new Actions(driver);
	}
	@AfterAll()
	public static void tearup() {
		driver.quit();
	}
	
	@Given("user navigate to {string}")
	public void user_navigate_to(String url) {
		driver.get(url);
	}
	@Then("verify that the home page is visible successfully")
	public void verify_that_the_home_page_is_visible_successfully() {
		String HomePage = driver.getTitle();
	    System.out.println(HomePage);
	}
	
	// Scenario 6
	
	@When("i click contact us")
	public void i_click_contact_us() {
		 driver.findElement(By.linkText("Contact us")).click();
	}
	@Then("verify {string} is displayed")
	public void verify_is_displayed(String ExpectedContactmsg) {
		WebElement ActualContactmsg = driver.findElement(By.xpath("//*[@id='contact-page']/div[2]/div[1]/div/h2"));
		String actualMessage = ActualContactmsg.getText().trim();
		Assert.assertTrue(actualMessage.equalsIgnoreCase(ExpectedContactmsg), "Expected message not displayed. Found: " + actualMessage);
	}
	@Then("enter name email subject and msg")
	public void enter_name_email_subject_and_msg() {
		 driver.findElement(By.name("name")).sendKeys(OrderSteps.getData("sheet1",1,0));
		    driver.findElement(By.name("email")).sendKeys(OrderSteps.getData("sheet1",1,15));
		    driver.findElement(By.name("subject")).sendKeys(OrderSteps.getData("sheet1",1,18));
		    driver.findElement(By.id("message")).sendKeys(OrderSteps.getData("sheet1",1,19));
	}
	@Then("upload file")
	public void upload_file() {
		WebElement uploadFile=driver.findElement(By.name("upload_file"));
	    uploadFile.sendKeys("C:/Users/sivas/Downloads/ignore.docx");
	}
	@Then("user click submit")
	public void user_click_submit() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement submitButton = driver.findElement(By.name("submit"));
		js.executeScript("arguments[0].scrollIntoView(true);", submitButton);
		Thread.sleep(500); // Small delay
		submitButton.click();

	}
	@Then("click ok")
	public void click_ok() {
		Alert a = driver.switchTo().alert();
		a.accept();
	}
	@Then("verify success msg {string}")
	public void verify_success_msg(String expected) {
		WebElement ActualSuccessmsg = driver.findElement(By.className("alert-success"));
	    String successMessage = ActualSuccessmsg.getText().trim();
	    Assert.assertTrue(successMessage.equalsIgnoreCase(expected)," Expected message not displayed. Found: " + successMessage);
	}
	@Then("click home button and verify it is in homepage")
	public void click_home_button_and_verify_it_is_in_homepage() {
		driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[1]/a")).click();
	    String Hometitle=driver.getTitle();
	    System.out.println(Hometitle);
	}
	
	
	// Scenario 7
	
	@When("click test cases")
	public void click_test_cases() {
	 driver.findElement(By.linkText("Test Cases")).click();   
	}
	@Then("verify in test cases page")
	public void verify_in_test_cases_page() {
	    String TestcasesPage = driver.getTitle();
	    System.out.println(TestcasesPage);
	}
	
	
	// Scenario 8
	
	@When("user click products")
	public void user_click_products() {
		driver.findElement(By.partialLinkText(" Products")).click(); 
	}
	@Then("verify products page")
	public void verify_products_page() {
	    String productPage = driver.getTitle();
	    System.out.println(productPage);
	}
	@Then("product list visible")
	public void product_list_visible() {
	    String allProducts = driver.findElement(By.xpath("/html/body/section[2]/div/div/div[2]/div/h2")).getText().trim();
	    System.out.println(allProducts);	    
	}
	@Then("click view products")
	public void click_view_products() {
		WebElement element = driver.findElement(By.xpath("/html/body/section[2]/div/div/div[2]/div/div[2]/div/div[2]/ul/li/a"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);

	}
	@Then("user in product detail page")
	public void user_in_product_detail_page() {
		String expectedTitle = "Automation Exercise - Product Details";
	    String actualTitle = driver.getTitle();
	    Assert.assertEquals(actualTitle, expectedTitle);
	}
	@Then("verify deatls page")
	public void verify_deatls_page() {

		WebElement productName = driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div[2]/div[2]/div/h2"));
		Assert.assertTrue(productName.isDisplayed(), "Product name is not visible");

		WebElement category = driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div[2]/div[2]/div/p[1]"));
		Assert.assertTrue(category.isDisplayed(), "Product category is not visible");

		WebElement price = driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div[2]/div[2]/div/span/span"));
		Assert.assertTrue(price.isDisplayed(), "Product price is not visible");

		WebElement availability = driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div[2]/div[2]/div/p[2]"));
		Assert.assertTrue(availability.isDisplayed(), "Product availability is not visible");

		WebElement condition = driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div[2]/div[2]/div/p[3]"));
		Assert.assertTrue(condition.isDisplayed(), "Product condition is not visible");

		WebElement brand = driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div[2]/div[2]/div/p[4]"));
		Assert.assertTrue(brand.isDisplayed(), "Product brand is not visible");
	}
	
	
	// Scenario 9
	
	@When("click on products")
	public void click_on_products() {
		driver.findElement(By.partialLinkText(" Products")).click();
	}
	@Then("verify all products page")
	public void verify_all_products_page() {
		String expectedTitle = "Automation Exercise - All Products";
	    String actualTitle = driver.getTitle();
	    Assert.assertEquals(actualTitle, expectedTitle);
	}
	@Then("search product name")
	public void search_product_name() {
	    driver.findElement(By.id("search_product")).sendKeys("shirt");
	    driver.findElement(By.id("submit_search")).click();
	}
	@Then("verify searched products page")
	public void verify_searched_products_page() {
		String searchProducts = driver.findElement(By.xpath("/html/body/section[2]/div/div/div[2]/div/h2")).getText().trim();
	    System.out.println(searchProducts);
	}
	@Then("verify all related products")
	public void verify_all_related_products() {
		List<WebElement> productList = driver.findElements(By.xpath("//div[@class='product-list']//h2"));
		for (WebElement product : productList) {
		    Assert.assertTrue(product.getText().toLowerCase().contains("shirt"), "Product does not match the search criteria: " + product.getText());
		}
	}
	

	
	// Scenario 10
	
	@When("scroll down to footer")
	public void scroll_down_to_footer() {
		JavascriptExecutor j = (JavascriptExecutor)driver;
		j.executeScript("window.scrollBy(0,5000)");
	}
	@Then("verify subscription")
	public void verify_subscription() {
	   String subscription= driver.findElement(By.xpath("//*[@id=\"footer\"]/div[1]/div/div/div[2]/div/h2")).getText().trim();
	   System.out.println(subscription);
	}
	@Then("enter email and click arrow")
	public void enter_email_and_click_arrow() {
	    driver.findElement(By.id("susbscribe_email")).sendKeys(OrderSteps.getData("sheet1",1,15));
	    driver.findElement(By.id("subscribe")).click();
	}
	@Then("verify msg visible")
	public void verify_msg_visible() {
		WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'You have been successfully subscribed!')]")));  
		Assert.assertTrue(successMsg.isDisplayed(), "Subscription success message is not visible");
	}
	
	
	
	// Scenario 11
	
	@When("click cart and scroll to footer")
	public void click_cart_and_scroll_to_footer() {
	    driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[3]/a")).click();
	    JavascriptExecutor j = (JavascriptExecutor)driver;
		j.executeScript("window.scrollBy(0,5000)");
	}
	@Then("Verify subscription text")
	public void verify_subscription_text() {
		String subscription= driver.findElement(By.xpath("//*[@id=\"footer\"]/div[1]/div/div/div[2]/div/h2")).getText().trim();
		   System.out.println(subscription);
	}
	@Then("enter email and click arrow button")
	public void enter_email_and_click_arrow_button() {
		driver.findElement(By.id("susbscribe_email")).sendKeys(OrderSteps.getData("sheet1",1,15));
	    driver.findElement(By.id("subscribe")).click();
	}
	@Then("verify message visible")
	public void verify_message_visible() {
		WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'You have been successfully subscribed!')]")));  
		Assert.assertTrue(successMsg.isDisplayed(), "Subscription success message is not visible");
	}
	
	
	// Scenario 12
	
	@When("user click products button")
	public void user_click_products_button() {
	    driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[2]/a")).click();
	}
	@Then("hover to first product and add to cart")
	public void hover_to_first_product_and_add_to_cart() {
	    WebElement firstProduct = driver.findElement(By.xpath("/html/body/section[2]/div/div/div[2]/div/div[2]/div/div[1]/div[2]"));
	    actions.moveToElement(firstProduct).perform();
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    WebElement addToCartButton = driver.findElement(By.xpath("/html/body/section[2]/div/div/div[2]/div/div[2]/div/div[1]/div[2]/div/a"));
	    js.executeScript("arguments[0].click();", addToCartButton);
	}
	@Then("click continue shopping")
	public void click_continue_shopping() {
		WebElement continueShoppingButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='cartModal']/div/div/div[3]/button")));
		continueShoppingButton.click();
	}
	@Then("hover to second product and add to cart")
	public void hover_to_second_product_and_add_to_cart() {
		WebElement firstProduct = driver.findElement(By.xpath("/html/body/section[2]/div/div/div[2]/div/div[3]/div/div[1]/div[2]"));
	    actions.moveToElement(firstProduct).perform();
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    WebElement addToCartButton = driver.findElement(By.xpath("/html/body/section[2]/div/div/div[2]/div/div[3]/div/div[1]/div[2]/div/a"));
	    js.executeScript("arguments[0].click();", addToCartButton);
	}
	@Then("user click view cart")
	public void user_click_view_cart() {
		WebElement viewCart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"cartModal\"]/div/div/div[2]/p[2]/a/u")));
		viewCart.click();
	}
	@Then("verify both products in cart")
	public void verify_both_products_in_cart() {
		List<WebElement> cartItems = (List<WebElement>) driver.findElements(By.xpath("//*[@id=\"cart_info_table\"]/tbody/tr"));
		Assert.assertEquals(cartItems.size(), 2, "Both products are not in the cart");
	}
	@Then("verify price,quality and total price")
	public void verify_price_quality_and_total_price() {
		String price1 = driver.findElement(By.xpath("//*[@id=\"product-1\"]/td[3]")).getText();
		String quantity1 = driver.findElement(By.xpath("//*[@id=\"product-1\"]/td[4]/button")).getText();
		String total1 = driver.findElement(By.xpath("//*[@id=\"product-1\"]/td[5]")).getText();

		String price2 = driver.findElement(By.xpath("//*[@id=\"product-2\"]/td[3]")).getText();
		String quantity2 = driver.findElement(By.xpath("//*[@id=\"product-2\"]/td[4]/button")).getText();
		String total2 = driver.findElement(By.xpath("//*[@id=\"product-2\"]/td[5]")).getText();

		Assert.assertEquals(Integer.parseInt(total1.replaceAll("[^0-9]", "")),Integer.parseInt(price1.replaceAll("[^0-9]", "")) * Integer.parseInt(quantity1));
		Assert.assertEquals(Integer.parseInt(total2.replaceAll("[^0-9]", "")),Integer.parseInt(price2.replaceAll("[^0-9]", "")) * Integer.parseInt(quantity2));
	}
	
	
	// Scenario 13
	
	@When("click view product")
	public void click_view_product() {
		WebElement viewProduct = driver.findElement(By.xpath("/html/body/section[2]/div/div/div[2]/div[1]/div[2]/div/div[2]/ul/li/a"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", viewProduct);
		viewProduct.click();
	}
	@Then("verify the opened details")
	public void verify_the_opened_details() {
		WebElement productTitle = driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div[2]/div[2]/div/h2"));
	    Assert.assertTrue(productTitle.isDisplayed(), "Product details page is not opened");
	}
	@Then("increase quantity to four")
	public void increase_quantity_to_four() {
		WebElement quantityBox = driver.findElement(By.id("quantity"));
	    quantityBox.clear();
	    quantityBox.sendKeys(String.valueOf(4));
	}
	@When("click add cart")
	public void click_add_cart() {
		driver.findElement(By.className("cart")).click();
	}
	@When("click view cart popup")
	public void click_view_cart_popup() {
		WebElement viewCart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"cartModal\"]/div/div/div[2]/p[2]/a/u")));
		viewCart.click();
	}
	@Then("verify product quantity")
	public void verify_product_quantity() {
		WebElement quantityElement = driver.findElement(By.xpath("//*[@id=\"product-1\"]/td[4]/button")); 
		String actualQuantity = quantityElement.getText().trim();
		Assert.assertEquals(actualQuantity, "5", "Incorrect quantity in cart");


	}
	
}
