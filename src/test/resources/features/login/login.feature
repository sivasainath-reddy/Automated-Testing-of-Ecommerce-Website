Feature: E-commerce Website Automation Testing

Background:
    Given I navigate to page "http://automationexercise.com"
    Then I verify that the home page is visible successfully
    When I click on Signup Login button
    Then I verify "New User Signup!" is visible

Scenario: Register a new user
   
    Then I enter name and email address
    When I click Signup button to signup
    Then I verify that 'ENTER ACCOUNT INFORMATION' is visible
    And I fill in the account details
    And I click Create Account button
    Then I check that 'Account Created!' is visible
    When I click Continue
    Then the user should see the username 'siva'
    When I click Delete Account button
    Then I verify that 'Account Deleted!' is available
    And I click Continue button
    
Scenario: Verify with valid credentials
   	When I enter valid email address and password
   	Then click login 
   	And verify to see message username 'siva'
   	    
Scenario: Verify with invalid credentials
		When I enter invalid email address and password
   	Then click login button
   	And verify error
   	
Scenario: Logout User
		When enter email and password
		Then i click login button
		And verify message username 'siva'
		When click logout
		Then verify user is in login page
		
Scenario: Register User with existing email
		When i register
		Then click signup
		And verify error message