package stepDefinitions.purchase;

import java.io.File;
import java.time.Duration;

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
import stepDefinitions.order.OrderSteps;

public class purchaseSteps {
	
	
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

	@Given("navigate to page {string}")
	public void navigate_to_page(String url) {
		driver.get(url);
	}
	@When("verify that the home page is displayed")
	public void verify_that_the_home_page_is_displayed() {
		String pageTitle = driver.getTitle();
	    System.out.println(pageTitle);
	}
	
	
	// Scenario 23
	

		@Then("I click on Signup or Login button")
		public void i_click_on_signup_or_login_button() {
		    driver.findElement(By.partialLinkText("Login")).click();
		}
		@Then("fill deatils in signup and create account")
		public void fill_deatils_in_signup_and_create_account() {
			driver.findElement(By.name("name")).sendKeys(OrderSteps.getData("sheet1",1,0));
		    driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/input[3]")).sendKeys(OrderSteps.getData("sheet1",1,1));
		    driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/button")).click();
		    
		    driver.findElement(By.id("id_gender1")).click();
//		    WebElement genderRadio = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_gender1")));
//		    genderRadio.click();
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
		    
		    WebElement element = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/div/form/button"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			element.click();
		}
		@Then("verify {string} and click continue")
		public void verify_and_click_continue(String ex_title) {
			WebElement act_Title = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/h2/b"));
			if(act_Title.getText().trim().equals(ex_title)) {
				System.out.println("Conformed the account creation.");
			} else {
				System.out.println("Unable to conform the account creation.");
			}
			WebElement elements = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"form\"]/div/div/div/div/a")));
			elements.click();
		}
		@Then("the user should see the message username {string}")
		public void the_user_should_see_the_message_username(String username) {
			try {
				WebElement messageElement = driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[10]/a/b"));
				String actualMessage = messageElement.getText().trim();
				System.out.println("Logged in as " + actualMessage);
				Assert.assertTrue(actualMessage.equalsIgnoreCase(username)," Expected message not displayed. Found: " + actualMessage);
			} catch (Exception e) {
				System.out.println("Exception occurred: " + e.getMessage());
			}
		}
		@When("add items to cart")
		public void add_items_to_cart() {
			for (WebElement addToCartButton : driver.findElements(By.className("add-to-cart"))) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCartButton);
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartButton);
				WebElement continueShopping = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Continue Shopping')]")));
				continueShopping.click();
			}
		}
		@Then("click cart button to proceed")
		public void click_cart_button_to_proceed() {
			driver.findElement(By.partialLinkText("Cart")).click();
		}
		@Then("verify cart page is displayed")
		public void verify_cart_page_is_displayed() {
		    String cartTitle = driver.getTitle();
		    System.out.println(cartTitle);
		}
		@Then("click on proceed to checkout")
		public void click_on_proceed_to_checkout() {
		    driver.findElement(By.xpath("//*[@id=\"do_action\"]/div[1]/div/div/a")).click();
		}
		@Then("check billing address and shipping address are same")
		public void check_billing_address_and_shipping_address_are_same() {
			String deliveryAddress = driver.findElement(By.id("address_delivery")).getText().trim();
		    String billingAddress = driver.findElement(By.id("address_invoice")).getText().trim();
		    deliveryAddress = deliveryAddress.replace("YOUR DELIVERY ADDRESS\n", "").trim();
		    billingAddress = billingAddress.replace("YOUR BILLING ADDRESS\n", "").trim();
		    Assert.assertEquals(deliveryAddress, billingAddress, "Delivery address does not match!");
		}
		@Then("click delete account")
		public void click_delete_account() {
			driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[5]/a")).click();
		}
		@Then("verify {string} msg and click continue button")
		public void verify_msg_click_continue_button(String delete_title) {
			WebElement Delete_Title = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/h2/b"));
			if(Delete_Title.getText().trim().equals(delete_title)) {
				System.out.println("Conformed the account deletion.");
			} else {
				System.out.println("Unable to conform the account deletion.");
			} 
			driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/div/a")).click();
		}
		
		
		// Scenario 24
	
	
	@When("user should add product to cart")
	public void user_should_add_product_to_cart() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement addToCart = driver.findElement(By.className("add-to-cart"));
		js.executeScript("arguments[0].click();", addToCart);
	}
	@Then("click view cart")
	public void click_view_cart() {
		WebElement viewCart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='cartModal']/div/div/div[2]/p[2]/a")));
		viewCart.click();
	}
	@Then("user should verify the cart page is displayed")
	public void user_should_verify_the_cart_page_is_displayed() {
	    String cartTitle = driver.getTitle();
	    System.out.println(cartTitle);
	}
	@Then("click proceed to checkout")
	public void click_proceed_to_checkout() {
	    driver.findElement(By.className("check_out")).click();
	}
	@When("click register \\/ login")
	public void click_register_login() {
		WebElement viewCart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"checkoutModal\"]/div/div/div[2]/p[2]/a")));
		viewCart.click();
	}
	@Then("signup then create account")
	public void signup_then_create_account() {
		driver.findElement(By.name("name")).sendKeys(OrderSteps.getData("sheet1",1,0));
	    driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/input[3]")).sendKeys(OrderSteps.getData("sheet1",1,1));
	    driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/button")).click();
	    
	    driver.findElement(By.id("id_gender1")).click();
//	    WebElement genderRadio = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_gender1")));
//	    genderRadio.click();	    
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
	    
	    WebElement element = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/div/form/button"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
	}
	@Then("verify {string} then click continue button")
	public void verify_then_click_continue_button(String ex_title) {
		WebElement act_Title = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/h2/b"));
		if(act_Title.getText().trim().equals(ex_title)) {
			System.out.println("Conformed the account creation.");
		} else {
			System.out.println("Unable to conform the account creation.");
		}
		WebElement elements = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"form\"]/div/div/div/div/a")));
		elements.click();
	}
	@Then("the user should see the logged in username {string}")
	public void the_user_should_see_the_logged_in_username(String username) {
		try {
			WebElement messageElement = driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[10]/a/b"));
			String actualMessage = messageElement.getText().trim();
			System.out.println("Logged in as " + actualMessage);
			Assert.assertTrue(actualMessage.equalsIgnoreCase(username)," Expected message not displayed. Found: " + actualMessage);
		} catch (Exception e) {
			System.out.println("Exception occurred: " + e.getMessage());
		}
	}
	@Then("click cart")
	public void click_cart() {
	    driver.findElement(By.partialLinkText("Cart")).click();
	}
	@Then("click button proceed to checkout")
	public void click_button_proceed_to_checkout() {
		 driver.findElement(By.className("check_out")).click();   
	}
	@When("verify address details and review order")
	public void verify_address_details_and_review_order() {
		String deliveryAddress = driver.findElement(By.id("address_delivery")).getText().trim();
	    String billingAddress = driver.findElement(By.id("address_invoice")).getText().trim();
	    deliveryAddress = deliveryAddress.replace("YOUR DELIVERY ADDRESS\n", "").trim();
	    billingAddress = billingAddress.replace("YOUR BILLING ADDRESS\n", "").trim();
	    Assert.assertEquals(deliveryAddress, billingAddress, "Delivery address does not match!");
	    
	    Assert.assertTrue(driver.findElement(By.id("product-1")).isDisplayed(), "Check product again");
	}
	@Then("enter description and click place order")
	public void enter_description_and_click_place_order() {
	    driver.findElement(By.name("message")).sendKeys("send order fastly");
	    driver.findElement(By.className("check_out")).click();
	}
	@Then("enter payment details")
	public void enter_payment_details() {
		driver.findElement(By.name("name_on_card")).sendKeys(OrderSteps.getData("sheet1",1,10));
	    driver.findElement(By.name("card_number")).sendKeys(OrderSteps.getData("sheet1",1,11));
	    driver.findElement(By.name("cvc")).sendKeys(OrderSteps.getData("sheet1",1,12));
	    driver.findElement(By.name("expiry_month")).sendKeys(OrderSteps.getData("sheet1",1,13));
	    driver.findElement(By.name("expiry_year")).sendKeys(OrderSteps.getData("sheet1",1,14));
	}
	@When("click pay and confirm order")
	public void click_pay_and_confirm_order() {
		WebElement Conform = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submit")));
		Conform.click();
	}
	@Then("verify success message {string}")
	public void verify_success_message(String orderConfermation) {
		WebElement act_Title = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/p"));
		if(act_Title.getText().trim().equals(orderConfermation)) {
			System.out.println("Order has successfully placed");
		} else {
			System.out.println("Order was not placed");
		}
	}
	@Then("click download invoice and verify invoice is download successfully")
	public void click_download_invoice_and_verify_invoice_is_download_successfully() throws InterruptedException {
		WebElement elements = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("check_out")));
		elements.click();
		Thread.sleep(5000);
	    String downloadPath = "C:\\Users\\sivas\\Downloads";
	    File invoice = new File(downloadPath + "\\invoice.txt");
	    Assert.assertTrue(invoice.exists(), "Invoice not downloaded successfully.");

	}
	@Then("click continue button")
	public void click_continue_button() {
	    driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/div/a")).click();
	}
	@Then("delete the account")
	public void delete_the_account() {
	    driver.findElement(By.partialLinkText("Delete Account")).click();
	}
	@Then("verify the msg {string} and click continue")
	public void verify_the_msg_and_click_continue(String delete_title) {
		WebElement Delete_Title = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/h2/b"));
		if(Delete_Title.getText().trim().equals(delete_title)) {
			System.out.println("Conformed the account deletion.");
		} else {
			System.out.println("Unable to conform the account deletion.");
		} 
		driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/div/a")).click();
	}

	
	// Scenario 25
	
	@When("scroll page down")
	public void scroll_page_down() {
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}
	@Then("verify {string} visible")
	public void verify_visible(String text) throws InterruptedException {
		Thread.sleep(3000);
		String subscription_Text= driver.findElement(By.xpath("//*[@id=\"footer\"]/div[1]/div/div/div[2]/div/h2")).getText();
	    Assert.assertTrue(subscription_Text.equalsIgnoreCase(text), "Displayed text: " + subscription_Text);
	}
	@Then("Click up arrow to move page upward")
	public void click_up_arrow_to_move_page_upward() {
		if ((Boolean) js.executeScript("return document.getElementById('aswift_3_host') !== null;")) {
		    js.executeScript("document.getElementById('aswift_3_host').remove();");
		}
		WebElement scrollUpButton = driver.findElement(By.id("scrollUp"));
		js.executeScript("arguments[0].click();", scrollUpButton);
	}
	@Then("verify text {string}")
	public void verify_text(String message) {
		WebElement verifyText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(), 'Full-Fledged practice website for Automation Engineers')]")));
	    Assert.assertTrue(verifyText.isDisplayed(), "Expected text is not visible");
	}
	
	
	// Scenario 26
	
	@When("page scroll down")
	public void page_scroll_down() {
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}
	@Then("verify {string} text visible")
	public void verify_text_visible(String text) throws InterruptedException {
		Thread.sleep(3000);
	    String subscription_Text= driver.findElement(By.xpath("//*[@id=\"footer\"]/div[1]/div/div/div[2]/div/h2")).getText();
	    Assert.assertTrue(subscription_Text.equalsIgnoreCase(text), "Displayed text: " + subscription_Text);
	}
	@Then("scroll page up")
	public void scroll_page_up() {
		js.executeScript("window.scrollTo(0, 0)");
	}
	@Then("verify text {string} is visible")
	public void verify_text_is_visible(String message) {
		WebElement verifyText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(), 'Full-Fledged practice website for Automation Engineers')]")));
	    Assert.assertTrue(verifyText.isDisplayed(), "Expected text is not visible");
	}
	
	
}
