//package stepDefinitions;
//
//import stepDefinitions.SignupPage;
//import stepDefinitions.SigninPage;
//import stepDefinitions.CartPage;
//import stepDefinitions.PaymentPage;
//import stepDefinitions.OrderConfirmationPage;
//import Utility.BrowserDriver;
//import Utility.Logger;
//import io.cucumber.java.After;
//import io.cucumber.java.Before;
//import io.cucumber.java.en.*;
//import org.junit.jupiter.api.Assertions;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import java.time.Duration;
//
//public class E_commerce {
//    private WebDriver driver;
//    private WebDriverWait wait;
//    private SignupPage signupPage;
//    private SigninPage signinPage;
//    private CartPage cartPage;
//    private PaymentPage paymentPage;
//    private OrderConfirmationPage orderConfirmationPage;
//    
//    @Before
//    public void setup() {
//        driver = BrowserDriver.getDriver("chrome");
//        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        signupPage = new SignupPage(driver, wait);
//        signinPage = new SigninPage(driver, wait);
//        cartPage = new CartPage(driver, wait);
//        paymentPage = new PaymentPage(driver, wait);
//        orderConfirmationPage = new OrderConfirmationPage(driver, wait);
//    }
//    
//    @After
//    public void teardown() {
//        BrowserDriver.quitDriver();
//    }
//    
//    @Given("User is on the signup page")
//    public void user_is_on_the_signup_page() {
//        signupPage.navigateToSignup();
//    }
//    
//    @When("User fills in details and creates an account")
//    public void fill_details_in_signup_and_create_account() {
//        signupPage.enterUserDetails("John", "Doe", "john.doe@example.com", "SecurePass123");
//        signupPage.submitSignup();
//    }
//    
//    @Then("User should be registered successfully")
//    public void user_should_be_registered_successfully() {
//        Assertions.assertTrue(signupPage.isSignupSuccessful());
//    }
//    
//    @Given("User is logged in")
//    public void user_is_logged_in() {
//        signinPage.login("john.doe@example.com", "SecurePass123");
//        Assertions.assertTrue(signinPage.isLoginSuccessful());
//    }
//    
//    @When("User adds an item to the cart")
//    public void user_adds_an_item_to_the_cart() {
//        cartPage.addItemToCart("Laptop");
//        Assertions.assertTrue(cartPage.isItemAdded());
//    }
//    
//    @And("User proceeds to checkout")
//    public void user_proceeds_to_checkout() {
//        cartPage.checkout();
//    }
//    
//    @And("User completes payment")
//    public void user_completes_payment() {
//        paymentPage.enterPaymentDetails("4111111111111111", "12/26", "123");
//        paymentPage.submitPayment();
//    }
//    
//    @Then("User should see the order confirmation message")
//    public void user_should_see_the_order_confirmation_message() {
//        Assertions.assertTrue(orderConfirmationPage.isOrderSuccessful());
//    }
//}