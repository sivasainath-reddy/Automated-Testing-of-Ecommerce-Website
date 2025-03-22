Feature: E-commerce Website Automation Testing

Background:
    Given I navigate to "http://automationexercise.com" website
    
Scenario: Search Products and Verify Cart After Login
		When click products
		Then veify the navigated page
		When search for product and click search
		Then verify the page searched products
		And verify all relate products are visible
		And add those products to cart
		And click cart and verify products are visible
		When click login and enter login credentials
		Then go to cart
		And verify all products added to cart are visible
		And remove all products from cart
		And verify message "Cart is empty! Click here to buy products."
		 
Scenario: Add review on product
		When i click products button
		Then veify the navigated to all products page
		And click on view products
		When verify "Write Your Review" is visible
		Then enter name email and review
		And click submit
		And verify review message
		
Scenario: Add to cart from Recommended items
		When scroll to bottom
		Then verify "recommended items" on bottom of page
		And add to cart recommended items
		And click on view cart button
		And verify product displayed in cart