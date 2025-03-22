Feature: E-commerce Website Automation Testing

Background:
    Given navigate to page "http://automationexercise.com"
    When verify that the home page is displayed
    
Scenario: Verify address details in checkout page
    When I click on Signup or Login button
    Then fill deatils in signup and create account
    And verify "Account Created!" and click continue
    And the user should see the message username 'siva'
    When add items to cart
    Then click cart button to proceed
    And verify cart page is displayed
    And click on proceed to checkout
    And check billing address and shipping address are same
    And click delete account
    And verify "Delete Account!" msg and click continue button 
    
Scenario: Download Invoice after purchase order
		When user should add product to cart
		Then click view cart
		And user should verify the cart page is displayed
		And click proceed to checkout
		When click register / login 
		Then signup then create account
		And verify "Account Created!" then click continue button
		And the user should see the logged in username 'siva'
		And click cart
		And click button proceed to checkout
		When verify address details and review order
		Then enter description and click place order
		And enter payment details
		When click pay and confirm order
		Then verify success message "Congratulations! Your order has been confirmed!"
		And click download invoice and verify invoice is download successfully
		And click continue button
		And delete the account
		And verify the msg "Account Deleted!" and click continue

Scenario: Verify Scroll Up using 'Arrow' button and Scroll Down functionality
		When page scroll down
		Then verify "Subscription" text visible
		And Click up arrow to move page upward
		And verify text "Full-Fledged practice website for Automation Engineers"
		
Scenario: Verify Scroll Up without 'Arrow' button and Scroll Down functionality
		When scroll page down
		Then verify "Subscription" visible
		And scroll page up
		And verify text "Full-Fledged practice website for Automation Engineers" is visible