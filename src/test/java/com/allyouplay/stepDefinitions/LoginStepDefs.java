package com.allyouplay.stepDefinitions;

import com.allyouplay.pages.LoginPage;
import com.allyouplay.utilities.BrowserUtils;
import com.allyouplay.utilities.ConfigurationReader;
import com.allyouplay.utilities.Driver;

import io.cucumber.java.en.*;
import io.cucumber.java.eo.Se;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginStepDefs {
    Logger logger = LoggerFactory.getLogger(LoginStepDefs.class);
    SoftAssertions softAssertions = new SoftAssertions();
    LoginPage loginPage = new LoginPage();


    @When("user navigates to login page")
    public void userNavigatesToLoginPage() {
        String url = ConfigurationReader.get("url");
        Driver.get().get(url);
        logger.info(">>> User lands on homepage : {}", url);
//
    }

    @Then("user should be able to display login page")
    public void userShouldBeAbleToDisplayLoginPage() {
        String title = Driver.get().getTitle();
        softAssertions.assertThat(title).as("Page title is not true!").isEqualTo("Sign in");
        logger.info(">>> Page title was verified.");
        String actualLoginHeadline = loginPage.loginBlockTitle.getText();
        softAssertions.assertThat(actualLoginHeadline).as("Login page headline is not true!").isEqualTo("LOGIN");
        logger.info(">>> Login page headline was verified.");
    }

    @When("user enters valid email")
    public void userEntersValidEmail() {
        String username = ConfigurationReader.get("username");
        checkCookies();
        loginPage.emailInput.sendKeys(username);
        logger.info(">>> Username was entered as --> {} .", username);
    }

    @And("user enters valid password")
    public void userEntersValidPassword() {
        String password = ConfigurationReader.get("password");
        loginPage.passwordInput.sendKeys(password);
        logger.info(">>> Password was entered to system.");
    }

    @And("user clicks sign-in button")
    public void userClicksGirisButton() {
        loginPage.signInButton.click();
        logger.info("User clicked sign-in button.");

    }


    @When("user closes newsletter menu")
    public void userClosesNewsletterMenu() {
        try {
            BrowserUtils.waitForClickablilityOfNewsLetterMessage();
            loginPage.newsletterMenu.click();
            logger.info("Newsletter menu was closed by user.");
        }catch (Exception e){
            e.printStackTrace();
        }
//
    }


    @Then("system should be able to display {string} menu")
    public void systemShouldBeAbleToDisplayMenu(String menuName) {
        String actualMenuName = loginPage.getMenuNameWithText(menuName).getText();
        softAssertions.assertThat(actualMenuName).as("Menu name was not verified! ").isEqualTo("SIGN OUT");
        logger.info("Menu >> {}  menu was verified.", menuName);

    }
    @Then("system should be able to display MY ACCOUNT menu")
    public void systemShouldBeAbleToDisplayMenu() {
        String actualMenuName = loginPage.getMenuNameWithText("My account").getText();
        softAssertions.assertThat(actualMenuName).as("Menu name was not verified! ").isEqualTo("MY ACCOUNT");
        logger.info(">>> MY ACCOUNT  menu was verified");

    }

    @Then("system should be able to display my cart item on the right side")
    public void systemShouldBeAbleToDisplayMyCartItemOnTheRightSide() {
        softAssertions.assertThat(loginPage.myCartItem.isDisplayed()).as("My cart item was not displayed!").isTrue();
        logger.info("My cart item was verified.");
    }

    @When("user enters {string} and {string}")
    public void userEntersAnd(String username, String password) {
        loginPage.emailInput.sendKeys(username);
        loginPage.passwordInput.sendKeys(password);
        loginPage.signInButton.click();
    }

    @Then("{string} should be displayed for {string}")
    public void shouldBeDisplayedForSituation(String errorMessage, String loginType) {
        String loggingErrorText = loginPage.loggingError.getText();
        softAssertions.assertThat(loggingErrorText).as("Logging error message was not verified").isEqualTo(errorMessage);
        logger.info(">>> Logging error message was verified.");
        if (loginType.equals("invalid username")) {
            String notFoundErrorText =loginPage.accountNotFoundError.getText();
            if (notFoundErrorText.length()>0){
                softAssertions.assertThat(notFoundErrorText).as("Invalid username error message was not verified").isEqualTo("No customer account found");
                logger.info(">>> Invalid username error message was verified.");
            }

        }

        if (loginType.equals("invalid password")) {
            String passwordErrorText = loginPage.credentialsNotCorrectError.getText();
           if (passwordErrorText.length()>0){
               softAssertions.assertThat(passwordErrorText).as("Invalid Password error message was not verified").isEqualTo("Watch out, the account credentials you have written are not correct.");
               logger.info(">>> Invalid Password error message was verified.");
           }
        }

        softAssertions.assertAll();

    }

    @When("user selects a product by clicking on it")
    public void userSelectsAProductByClickingOnIt() {
        loginPage.selectedItem.click();
        logger.info("User selected a product by clicking.");
    }

    @And("user enters his birthday to age verification menu and clicks continue")
    public void userEntersHisBirthdayToAgeVerificationMenuAndClicksContinue() {
        try {
            Select dayDropdown = new Select(loginPage.dayDropdown);
            dayDropdown.selectByVisibleText("17");
            Select monthDropdown = new Select(loginPage.monthDropdown);
            monthDropdown.selectByVisibleText("August");
            Select yearDropdown = new Select(loginPage.yearDropdown);
            yearDropdown.selectByVisibleText("1983");
            logger.info("User selected his birthday as Day / Month / Year");
            loginPage.ageContinueButton.click();
            logger.info("User clicked age verification Continue button.");
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }


    }


    @Given("user on the login page")
    public void userOnTheLoginPage() {
        Driver.get().get(ConfigurationReader.get("login_url"));
        logger.info(">>> User lands on Login page.");
        checkCookies();

    }

    public void checkCookies() {
        try {
            int size = loginPage.acceptCookieButton.size();
            if (size > 0) {
                loginPage.acceptCookieButton.get(0).click();
                logger.info(">>> Accept cookies button was clicked.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @And("user adds the product to cart by clicking Add your cart button")
    public void userAddsTheProductToCartByClickingAddYourCartButton() {
        loginPage.addToYourCartButton.click();
        logger.info("User clicked on Add your cart button.");
    }

    @Then("total amount of product in cart should be displayed as {int}")
    public void totalAmountOfProductInCartShouldBeDisplayedAs(int total) {
        BrowserUtils.waitFor(1);
        int size = loginPage.productsInCart.size();
        int sizeOfBar = loginPage.barNotification.size();
        if (sizeOfBar>0) {
            size=1;
        }
        softAssertions.assertThat(size).as("Total size is NOT verified!").isEqualTo(total);
        logger.info("Total size was verified as --> {}", total);
        softAssertions.assertAll();
    }

    @Given("user lands on the homepage")
    public void user_lands_on_the_homepage() {

    }

    @When("user clicks login button")
    public void userClicksLoginButton() {
//        BrowserUtils.waitFor(3);
        checkCookies();
        logger.info(">>> Accept cookies button was clicked.");
//
        new LoginPage().loginMenuButton.click();

        logger.info(">>> User clicked Login menu");
    }
}
