package stepDefinitions.order;

import java.io.FileInputStream;
import java.time.Duration;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
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

public class OrderSteps {
	
	public static String getData(String sheetName, int rowNumber, int colNumber) {
	    String value = "";
	    try (FileInputStream fis = new FileInputStream("AccountCreate+Payment.xlsx");
	         Workbook workbook = new XSSFWorkbook(fis)) {

	        Sheet sheet = workbook.getSheet(sheetName);
	        if (sheet != null) {
	            Row row = sheet.getRow(rowNumber);
	            if (row != null) {
	                Cell cell = row.getCell(colNumber);
	                if (cell != null) {
	                    switch (cell.getCellType()) {
	                        case STRING:
	                            value = cell.getStringCellValue();
	                            break;
	                        case NUMERIC:
	                            value = String.valueOf((long) cell.getNumericCellValue());
	                            break;
	                        case BOOLEAN:
	                            value = String.valueOf(cell.getBooleanCellValue());
	                            break;
	                        default:
	                            value = "";
	                    }
	                }
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return value;
	}
	
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

	
	@Given("navigate to {string} website")
	public void navigate_to_website(String url) {
		driver.get(url);
	}
	@When("verify the home page is visible successfully")
	public void verify_the_home_page_is_visible_successfully() {
		String pageTitle = driver.getTitle();
	    System.out.println(pageTitle);   
	}
	
	//Scenario 14
	
	
	@When("add product to cart")
	public void add_product_to_cart() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement addToCart = driver.findElement(By.className("add-to-cart"));
		js.executeScript("arguments[0].click();", addToCart);
	}
	@Then("click view cart button")
	public void click_view_cart_button() {
		WebElement viewCart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='cartModal']/div/div/div[2]/p[2]/a")));
		viewCart.click();
	}
	@Then("user verify the cart page is displayed")
	public void user_verify_the_cart_page_is_displayed() {
	    String cartTitle = driver.getTitle();
	    System.out.println(cartTitle);
	}
	@Then("click proceed to checkout button")
	public void click_proceed_to_checkout_button() {
		driver.findElement(By.className("check_out")).click();
	}
	@When("click register or login")
	public void click_register_or_login() {
		WebElement viewCart = wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Register")));
		viewCart.click();
	}
	@Then("signup and create account")
	public void signup_and_create_account() {
		driver.findElement(By.name("name")).sendKeys(OrderSteps.getData("sheet1",1,0));
	    driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/input[3]")).sendKeys(OrderSteps.getData("sheet1",1,1));
	    driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/button")).click();
	    
	    WebElement genderRadio = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_gender1")));
	    genderRadio.click();
//	    driver.findElement(By.id("id_gender1")).click();
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
	@Then("verify {string} then click continue")
	public void verify_then_click_continue(String ex_title) {
		WebElement act_Title = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/h2/b"));
		if(act_Title.getText().trim().equals(ex_title)) {
			System.out.println("Conformed the account creation.");
		} else {
			System.out.println("Unable to conform the account creation.");
		}
		WebElement elements = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"form\"]/div/div/div/div/a")));
		elements.click();
	}
	@Then("the user should see the logged in as {string}")
	public void user_should_see_the_logged_in_as(String username) {
		try {
			WebElement messageElement = driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[10]/a/b"));
			String actualMessage = messageElement.getText().trim();
			System.out.println("Logged in as " + actualMessage);
			Assert.assertTrue(actualMessage.equalsIgnoreCase(username)," Expected message not displayed. Found: " + actualMessage);
		} catch (Exception e) {
			System.out.println("Exception occurred: " + e.getMessage());
		}
	}
	@Then("user click cart")
	public void user_click_cart() {
		driver.findElement(By.partialLinkText("Cart")).click();
	}
	@Then("proceed to checkout")
	public void proceed_to_checkout() {
		driver.findElement(By.className("check_out")).click();
	}
	@When("verify address details then review your order")
	public void verify_address_details_then_review_your_order() {
		String deliveryAddress = driver.findElement(By.id("address_delivery")).getText().trim();
	    String billingAddress = driver.findElement(By.id("address_invoice")).getText().trim();
	    deliveryAddress = deliveryAddress.replace("YOUR DELIVERY ADDRESS\n", "").trim();
	    billingAddress = billingAddress.replace("YOUR BILLING ADDRESS\n", "").trim();
	    Assert.assertEquals(deliveryAddress, billingAddress, "Delivery address does not match!");
	    
	    Assert.assertTrue(driver.findElement(By.id("product-1")).isDisplayed(), "Check product again");
	}
	@Then("enter description and then click place order")
	public void enter_description_and_then_click_place_order() {
		driver.findElement(By.name("message")).sendKeys("send order fastly");
	    driver.findElement(By.className("check_out")).click();
	}
	@Then("enter debit card details")
	public void enter_debit_card_details() {
		driver.findElement(By.name("name_on_card")).sendKeys(OrderSteps.getData("sheet1",1,10));
	    driver.findElement(By.name("card_number")).sendKeys(OrderSteps.getData("sheet1",1,11));
	    driver.findElement(By.name("cvc")).sendKeys(OrderSteps.getData("sheet1",1,12));
	    driver.findElement(By.name("expiry_month")).sendKeys(OrderSteps.getData("sheet1",1,13));
	    driver.findElement(By.name("expiry_year")).sendKeys(OrderSteps.getData("sheet1",1,14));
	}
	@When("click pay and confirm order to proceed")
	public void click_pay_and_confirm_order_to_proceed() {
		WebElement payment_conform = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submit")));
		payment_conform.click();
	}
	@Then("verify message {string} is visible")
	public void verify_message_is_visible(String orderConfermation) {
		WebElement act_Title = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/p"));
		if(act_Title.getText().trim().equals(orderConfermation)) {
			System.out.println("Order has successfully placed");
		} else {
			System.out.println("Order was not placed");
		}
	}
	@Then("delete account")
	public void delete_account() {
		driver.findElement(By.partialLinkText("Delete Account")).click();
	}
	@Then("verify the message {string} and click continue")
	public void verify_the_message_and_click_continue(String delete_title) {
		WebElement Delete_Title = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/h2/b"));
		if(Delete_Title.getText().trim().equals(delete_title)) {
			System.out.println("Conformed the account deletion.");
		} else {
			System.out.println("Unable to conform the account deletion.");
		} 
		driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/div/a")).click();
	}

	
	
	// Scenario 15
	
	@When("click signup login")
	public void click_signup_login() {
	    driver.findElement(By.partialLinkText("Signup")).click();
	}
	@Then("user signup and create account")
	public void user_signup_and_create_account() {
		driver.findElement(By.name("name")).sendKeys(OrderSteps.getData("sheet1",1,0));
	    driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/input[3]")).sendKeys(OrderSteps.getData("sheet1",1,1));
	    driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/button")).click();
	    
	    WebElement genderRadio = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_gender1")));
	    genderRadio.click();
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
	@Then("verify {string} and click continue button")
	public void verify_and_click_continue_button(String ex_title) {
		WebElement act_Title = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/h2/b"));
		if(act_Title.getText().trim().equals(ex_title)) {
			System.out.println("Conformed the account creation.");
		} else {
			System.out.println("Unable to conform the account creation.");
		}
		WebElement elements = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"form\"]/div/div/div/div/a")));
		elements.click();
	}
	@Then("user should see the logged in username {string}")
	public void user_should_see_the_logged_in_username(String username) {
		try {
			WebElement messageElement = driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[10]/a/b"));
			String actualMessage = messageElement.getText().trim();
			System.out.println("Logged in as " + actualMessage);
			Assert.assertTrue(actualMessage.equalsIgnoreCase(username)," Expected message not displayed. Found: " + actualMessage);
		} catch (Exception e) {
			System.out.println("Exception occurred: " + e.getMessage());
		}
	}
	@When("add product to the cart")
	public void add_product_to_the_cart() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement addToCart = driver.findElement(By.className("add-to-cart"));
		js.executeScript("arguments[0].click();", addToCart);
	}
	@Then("click view cart link")
	public void click_view_cart_link() {
		WebElement viewCart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='cartModal']/div/div/div[2]/p[2]/a")));
		viewCart.click();
	}
	@Then("verify the cart page is visible")
	public void verify_the_cart_page_is_visible() {
		String cartTitle = driver.getTitle();
	    System.out.println(cartTitle);
	}
	@Then("click the proceed to checkout")
	public void click_the_proceed_to_checkout() {
		driver.findElement(By.className("check_out")).click();
	}
	@When("verify address details and review your order")
	public void verify_address_details_and_review_your_order() {
		String deliveryAddress = driver.findElement(By.id("address_delivery")).getText().trim();
	    String billingAddress = driver.findElement(By.id("address_invoice")).getText().trim();
	    deliveryAddress = deliveryAddress.replace("YOUR DELIVERY ADDRESS\n", "").trim();
	    billingAddress = billingAddress.replace("YOUR BILLING ADDRESS\n", "").trim();
	    Assert.assertEquals(deliveryAddress, billingAddress, "Delivery address does not match!");
	    
	    Assert.assertTrue(driver.findElement(By.id("product-1")).isDisplayed(), "Check product again");
	}
	@Then("enter description then click place order")
	public void enter_description_then_click_place_order() {
		driver.findElement(By.name("message")).sendKeys("send order fastly");
	    driver.findElement(By.className("check_out")).click();
	}
	@Then("enter the payment details")
	public void enter_the_payment_details() {
		driver.findElement(By.name("name_on_card")).sendKeys(OrderSteps.getData("sheet1",1,10));
	    driver.findElement(By.name("card_number")).sendKeys(OrderSteps.getData("sheet1",1,11));
	    driver.findElement(By.name("cvc")).sendKeys(OrderSteps.getData("sheet1",1,12));
	    driver.findElement(By.name("expiry_month")).sendKeys(OrderSteps.getData("sheet1",1,13));
	    driver.findElement(By.name("expiry_year")).sendKeys(OrderSteps.getData("sheet1",1,14));
	}
	@When("click pay and confirm order button")
	public void click_pay_and_confirm_order_button() {
		WebElement payment_conform = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submit")));
		payment_conform.click();
	}
	@Then("verify order success msg {string}")
	public void verify_order_success_msg(String orderConfermation) {
		WebElement act_Title = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/p"));
		if(act_Title.getText().trim().equals(orderConfermation)) {
			System.out.println("Order has successfully placed");
		} else {
			System.out.println("Order was not placed");
		}
	}
	@Then("click delete account perminently")
	public void click_delete_account_perminently() {
		driver.findElement(By.partialLinkText("Delete Account")).click();
	}
	@Then("verify the msg {string} then click continue")
	public void verify_the_msg_then_click_continue(String delete_title) {
		WebElement Delete_Title = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/h2/b"));
		if(Delete_Title.getText().trim().equals(delete_title)) {
			System.out.println("Conformed the account deletion.");
		} else {
			System.out.println("Unable to conform the account deletion.");
		} 
		driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/div/a")).click();
	}
	
	
	
	// Scenario 16
	
	@When("user click signup login")
	public void user_click_signup_login() {
	    driver.findElement(By.partialLinkText("Login")).click();
	}
	@Then("Login")
	public void login() {
	    driver.findElement(By.name("email")).sendKeys(OrderSteps.getData("sheet1",1,15));
	    driver.findElement(By.name("password")).sendKeys(OrderSteps.getData("sheet1",1,16));
	    driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/form/button")).click();
	}
	@Then("the user should see if logged in username {string} visible")
	public void the_user_should_see_if_logged_in_username_visible(String username) {
			WebElement messageElement = driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[10]/a/b"));
			String actualMessage = messageElement.getText().trim();
			System.out.println("Logged in as " + actualMessage);
			Assert.assertTrue(actualMessage.equalsIgnoreCase(username)," Expected message not displayed. Found: " + actualMessage);
	}
	@When("user add product to cart")
	public void user_add_product_to_cart() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement addToCart = driver.findElement(By.className("add-to-cart"));
		js.executeScript("arguments[0].click();", addToCart);
	}
	@Then("click viewcart")
	public void click_viewcart() {
		WebElement viewCart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='cartModal']/div/div/div[2]/p[2]/a")));
		viewCart.click();
	}
	@Then("verify cart page is visible")
	public void verify_cart_page_is_visible() {
		String cartTitle = driver.getTitle();
	    System.out.println(cartTitle);
	}
	@Then("user click proceed to checkout")
	public void user_click_proceed_to_checkout() {
		driver.findElement(By.className("check_out")).click();
	}
	@When("check address details then review order")
	public void check_address_details_then_review_order() {
		String deliveryAddress = driver.findElement(By.id("address_delivery")).getText().trim();
	    String billingAddress = driver.findElement(By.id("address_invoice")).getText().trim();
	    deliveryAddress = deliveryAddress.replace("YOUR DELIVERY ADDRESS\n", "").trim();
	    billingAddress = billingAddress.replace("YOUR BILLING ADDRESS\n", "").trim();
	    Assert.assertEquals(deliveryAddress, billingAddress, "Delivery address does not match!");
	    
	    Assert.assertTrue(driver.findElement(By.id("product-1")).isDisplayed(), "Check product again");
	}
	@Then("enter description to place order")
	public void enter_description_to_place_order() {
		driver.findElement(By.name("message")).sendKeys("send order fastly");
	    driver.findElement(By.className("check_out")).click();
	}
	@Then("enter card details")
	public void enter_card_details() {
		driver.findElement(By.name("name_on_card")).sendKeys(OrderSteps.getData("sheet1",1,10));
	    driver.findElement(By.name("card_number")).sendKeys(OrderSteps.getData("sheet1",1,11));
	    driver.findElement(By.name("cvc")).sendKeys(OrderSteps.getData("sheet1",1,12));
	    driver.findElement(By.name("expiry_month")).sendKeys(OrderSteps.getData("sheet1",1,13));
	    driver.findElement(By.name("expiry_year")).sendKeys(OrderSteps.getData("sheet1",1,14));
	}
	@When("user click pay and confirm order")
	public void user_click_pay_and_confirm_order() {
		WebElement payment_conform = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submit")));
		payment_conform.click();
	}
	@Then("verify success message {string} displayed")
	public void verify_success_message_displayed(String orderConfermation) {
		WebElement act_Title = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/p"));
		if(act_Title.getText().trim().equals(orderConfermation)) {
			System.out.println("Order has successfully placed");
		} else {
			System.out.println("Order was not placed");
		}
	}
	
	
	
	// Scenario 17
	
	@When("add products to cart")
	public void add_products_to_cart() {
		for (WebElement addToCartButton : driver.findElements(By.className("add-to-cart"))) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCartButton);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartButton);
			WebElement continueShopping = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Continue Shopping')]")));
			continueShopping.click();
		}
	}
	@Then("click cart button")
	public void click_cart_button() {
	    driver.findElement(By.partialLinkText("Cart")).click();
	}
	@Then("verify the cart page")
	public void verify_cart_page() {
		String cartTitle = driver.getTitle();
	    System.out.println(cartTitle);
	}
	@Then("remove the product from cart")
	public void remove_the_product_from_cart() {
		for (WebElement remove : driver.findElements(By.className("cart_quantity_delete"))) {
		    try {
		        wait.until(ExpectedConditions.elementToBeClickable(remove)).click();
		    } catch (ElementClickInterceptedException e) {
		        JavascriptExecutor js = (JavascriptExecutor) driver;
		        js.executeScript("arguments[0].click();", remove);
		    }
		}
	}
	@Then("verify if the product is reomved")
	public void verify_if_the_product_is_reomved() {
		WebElement emptyCartMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("empty_cart")));
		String actualMessage = emptyCartMessage.getText();
		String expectedMessage = "Cart is empty! Click here to buy products.";
		Assert.assertTrue(actualMessage.contains(expectedMessage), "Expected message not found!");
	}
	
	
	// Scenario 18
	
	@When("verify category in page")
	public void verify_category_in_page() {
	    String category =driver.findElement(By.xpath("/html/body/section[2]/div/div/div[1]/div/h2")).getText();
	    System.out.println(category);
	}
	@Then("select women category")
	public void select_women_category() {
		WebElement womenCategory = driver.findElement(By.xpath("//*[@id='accordian']/div[1]/div[1]/h4/a"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", womenCategory);
	}
	@Then("select a product")
	public void select_a_product() {
		WebElement womenCategory = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"Women\"]/div/ul/li[3]/a")));
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", womenCategory);
	    womenCategory.click();
	}
	@Then("verify the category page is displayed as {string}")
	public void verify_the_category_page_is_displayed_as(String expectedTitle) {
	    String actualTitle = driver.findElement(By.xpath("/html/body/section/div/div[2]/div[2]/div/h2")).getText();
	    Assert.assertTrue(actualTitle.equalsIgnoreCase(expectedTitle), "Title Displayed: "+actualTitle);
	}
	@When("select sub-category men")
	public void select_sub_category_men() {
		WebElement menCategory = driver.findElement(By.xpath("//*[@id=\"accordian\"]/div[2]/div[1]/h4/a"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", menCategory);
		WebElement menSubCategory = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"Men\"]/div/ul/li[2]/a")));
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", menSubCategory);
	    menSubCategory.click();
	}
	@Then("verify the navigated page")
	public void verify_the_navigated_page() {
	    String titleVerification = driver.findElement(By.xpath("/html/body/section/div/div[2]/div[2]/div/h2")).getText();
	    System.out.println(titleVerification);
	}
	
	
	//Scenario 19
	
	@When("click products button")
	public void click_products_button() {
	    driver.findElement(By.partialLinkText("Products")).click();
	}
	@Then("verify brands on left side")
	public void verify_brands_on_left_side() {
		WebElement brandTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'brands')]//h2")));
		System.out.println(brandTitle.getText());
	}
	@Then("select on any brand name")
	public void select_on_any_brand_name() {
		WebElement brand = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body > section > div > div.row > div.col-sm-3 > div > div.brands_products > div > ul > li:nth-child(1) > a")));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", brand);
	}
	@Then("verify navigated brand page and brand product are displayed")
	public void verify_navigated_brand_page_and_brand_product_are_displayed() {
		String titleVerification = driver.findElement(By.xpath("/html/body/section/div/div[2]/div[2]/div/h2")).getText();
	    System.out.println(titleVerification);
	}
	@When("select any other brand name")
	public void select_any_other_brand_name() {
		WebElement brand = driver.findElement(By.xpath("/html/body/section/div/div[2]/div[1]/div/div[2]/div/ul/li[3]/a"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", brand);
	}
	@Then("verify the brand page and product are visible")
	public void verify_the_brand_page_and_product_are_visible() {
		String titleVerification = driver.findElement(By.xpath("/html/body/section/div/div[2]/div[2]/div/h2")).getText();
	    System.out.println(titleVerification);
	}
	
}
