package com.allyouplay.pages;

import com.allyouplay.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LoginPage extends BasePage {


    public WebElement getMenuNameWithText(String menu) {
        return Driver.get().findElement(By.xpath("//ul[@class='list-plain list-horizontal list-menu-header is-ml-auto']//a[.='"+menu+"']"));
    }

    @FindBy (xpath = "//div[text()='Sign up to our newsletter to receive giveaways & special offers, occasionally. No spam! :)']")
    public WebElement newsLetterMessage;

    @FindBy (css ="div#bar-notification")
    public List<WebElement> barNotification;

}
