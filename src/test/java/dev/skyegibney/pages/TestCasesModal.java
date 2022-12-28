package dev.skyegibney.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TestCasesModal {
    public TestCasesModal(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="/html/body/div[3]/div/div/h3")
    public WebElement testCaseHeading;

    @FindBy(xpath="/html/body/div[3]/div/div/p[6]")
    public WebElement performedBy;

    @FindBy(xpath="/html/body/div[3]/div/div/button[2]")
    public WebElement editButton;

    @FindBy(xpath="/html/body/div[3]/div/div/button[1]")
    public WebElement closeButton;
}
