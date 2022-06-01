@login
Feature: LOGIN FUNCTIONALITY
  # As a user I want to be able Login to ALLYOUPLAY.COM - Official Game Store .
  # So that I can buy products that I want.

  # Acceptance Criteria
  # I can login to website by entering username/password. To login to the application, firstly open to ALLYOUPLAY.COM - Official Game Store from the browser, and click Login button top right of screen. After login screen is loaded then enter username/password combination to username and password textbox and click the Login Button.
  #if I entered the correct username/password then site should navigate me to the home page and show my account and logout menus and current basket information top right of screen.
  #If username or password that I entered is wrong then an information message should be displayed

  @positive_Login
  Scenario: Verification of Login Functionality - Happy Path
    When user navigates to login page
    When user clicks login button
    Then user should be able to display login page
    When user enters valid email
    And  user enters valid password
    And  user clicks sign-in button
    And  user closes newsletter menu
    Then system should be able to display MY ACCOUNT menu
    Then system should be able to display "Sign out" menu
    Then system should be able to display my cart item on the right side
    When user selects a product by clicking on it
    And  user enters his birthday to age verification menu and clicks continue
    And  user adds the product to cart by clicking Add your cart button
    Then total amount of product in cart should be displayed as 1


  @negative
  Scenario Outline:  Verification of Login Functionality - Negative with Invalid Credentials
    Given user on the login page
    When user enters "<username>" and "<password>"
    And  user clicks sign-in button
    Then "<Error message>" should be displayed for "<invalid credential>"

    Examples:
      | invalid credential | username            | password | Error message                                                      |
      | invalid username   | esadesad@gmail.com  | 56Qa.74_ | Oh no! There is an error and you need to fix it before logging in. |
      | invalid password   | esadersoy@gmail.com | 12345Abc | Oh no! There is an error and you need to fix it before logging in. |
      | empty username     |                     | 56Qa.74_ | Oh no! There is an error and you need to fix it before logging in. |
      | empty password     | esadersoy@gmail.com |          | Oh no! There is an error and you need to fix it before logging in. |



  @screenshot
  Scenario:  Verification of Login Functionality with Invalid Credentials - Screenshot Example
    Given user on the login page
    When user enters "esadesad@gmail.com" and "56Qa.74_"
    And  user clicks sign-in button
    Then "Error message" should be displayed for "invalid username"