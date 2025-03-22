Feature: E-commerce Website Automation Testing

Background:
    Given user navigate to "http://automationexercise.com"
    Then verify that the home page is visible successfully
    
Scenario: Contact Us
    When i click contact us
    Then verify 'Get In Touch' is displayed
    And enter name email subject and msg
    And upload file
    And user click submit
    And click ok
    And verify success msg 'Success! Your details have been submitted successfully.'
    And click home button and verify it is in homepage
    
Scenario: Verify Test Cases Page
		When click test cases
		Then verify in test cases page
		
Scenario: Verify All Products and product detail page
		 When user click products
		 Then verify products page
		 And product list visible
		 And click view products
		 And user in product detail page
		 And verify deatls page
		 
Scenario: Search Product
		When click on products
		Then verify all products page
		And search product name
		And verify searched products page
		And verify all related products
		
Scenario: Verify Subscription in home page
		When scroll down to footer
		Then verify subscription
		And enter email and click arrow
		And verify msg visible
		
Scenario: Verify Subscription in Cart page
		When click cart and scroll to footer
		Then Verify subscription text
		And enter email and click arrow button
		And verify message visible
		
Scenario: Add Products in Cart
		When user click products button
		Then hover to first product and add to cart
		And click continue shopping
		And hover to second product and add to cart
		And user click view cart
		And verify both products in cart
		And verify price,quality and total price
		
Scenario: Verify Product quantity in Cart
		When click view product
		Then verify the opened details
		And increase quantity to four
		When click add cart
		And click view cart popup
		Then verify product quantity