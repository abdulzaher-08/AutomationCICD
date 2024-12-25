
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

Background:
Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with username <username> and password <password>
    When I add product <productName> to the cart
    And Checkout the <productName> and submit the order
    Then verify "Thankyou for the order." is displayed on ConfirmationPage

    Examples: 
      | username  						| password |	productName	|
      | bilalahmed786@gmail.com 	| 12345Aa@ |	IPHONE 13 PRO	|
