package com.allyouplay.pages;



import com.allyouplay.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public abstract class BasePage {
    public BasePage() {

        PageFactory.initElements(Driver.get(), this);

    }


    @FindBy (xpath ="//button[text()='Accept And Proceed to Website']")
    public List<WebElement> acceptCookieButton;

    @FindBy(xpath = "//div[@class='block-title']")
    public WebElement loginBlockTitle;

    @FindBy (xpath = "//input[@id='Email']")
    public WebElement emailInput;

    @FindBy (xpath = "//input[@id='Password']")
    public WebElement passwordInput;

    @FindBy (xpath = "//div[@class='form-group']/button[@type='submit']")
    public WebElement signInButton;

    @FindBy (xpath = "//div[@class='soundest-form-without-image-close-holder']/a[1]")
    public WebElement newsletterMenu;

    @FindBy (xpath = "//span[.='Oh no! There is an error and you need to fix it before logging in.']")
    public WebElement loggingError;

    @FindBy (css =".menu-icon-cart > .icon")
    public WebElement myCartItem;

    @FindBy (css = ".validation-summary-errors>ul>li")
    public WebElement accountNotFoundError;

    @FindBy (css = ".validation-summary-errors>ul>li")
    public WebElement credentialsNotCorrectError;

    @FindBy (xpath ="//div[@id='deals']//div[@class='owl-item active']//img[@alt='Ceres']")
    public WebElement selectedItem;

    @FindBy (css = "select#DateOfBirthDay")
    public WebElement dayDropdown;

    @FindBy (css = "select#DateOfBirthMonth")
    public WebElement monthDropdown;

    @FindBy (css ="select#DateOfBirthYear")
    public WebElement yearDropdown;

    @FindBy (xpath = "//button[@id='eu-age-ok']")
    public WebElement ageContinueButton;

    @FindBy (css = ".is-flex-grow-1.btn")
    public WebElement addToYourCartButton;

    @FindBy(css = "div[class='row cart-product-row']")
    public List<WebElement> productsInCart;

    @FindBy (xpath = "(//a[text()='Login'])[1]")
    public WebElement loginMenuButton;







}
