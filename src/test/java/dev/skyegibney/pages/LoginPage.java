package dev.skyegibney.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//input[@name='username']")
    public WebElement usernameInput;

    @FindBy(xpath="//input[@name='pass']")
    public WebElement passwordInput;

    @FindBy(xpath="//button")
    public WebElement loginButton;
}
