Feature: E-commerce Website Automation Testing

Background:
    Given navigate to "http://automationexercise.com" website
    When verify the home page is visible successfully

Scenario: Place Order: Register while Checkout
		When add product to cart
		Then click view cart button
		And user verify the cart page is displayed
		And click proceed to checkout button
		When click register or login 
		Then signup and create account
		And verify "Account Created!" then click continue
		And the user should see the logged in as 'siva'
		And user click cart
		And proceed to checkout
		When verify address details then review your order
		Then enter description and then click place order
		And enter debit card details
		When click pay and confirm order to proceed
		Then verify message "Congratulations! Your order has been confirmed!" is visible
		And delete account
		And verify the message "Account Deleted!" and click continue
		
Scenario: Place Order: Register before Checkout
		When click signup login 
		Then user signup and create account
		And verify "Account Created!" and click continue button
		And user should see the logged in username 'siva'
		When add product to the cart
		Then click view cart link
		And verify the cart page is visible
		And click the proceed to checkout
		When verify address details and review your order
		Then enter description then click place order
		And enter the payment details
		When click pay and confirm order button
		Then verify order success msg "Congratulations! Your order has been confirmed!"
		And click delete account perminently
		And verify the msg "Account Deleted!" then click continue
		
Scenario: Place Order: Login before Checkout
		When user click signup login
		Then Login
		And the user should see if logged in username 'siva' visible
		When user add product to cart
		Then click viewcart
		And verify cart page is visible
		And user click proceed to checkout
		When check address details then review order
		Then enter description to place order
		And enter card details
		When user click pay and confirm order
		Then verify success message "Congratulations! Your order has been confirmed!" displayed
		
Scenario: Remove Products From Cart
		When add products to cart
		Then click cart button
		And verify the cart page
		And remove the product from cart
		And verify if the product is reomved
		
Scenario: View Category Products
		When verify category in page
		Then select women category
		And select a product
		And verify the category page is displayed as "Women - Saree Products"
		When select sub-category men
		Then verify the navigated page
		
Scenario: View & Cart Brand Products
    When click products button
    Then verify brands on left side
    And select on any brand name
    And verify navigated brand page and brand product are displayed
    When select any other brand name
    Then verify the brand page and product are visible