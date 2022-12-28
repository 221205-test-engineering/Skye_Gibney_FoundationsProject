package dev.skyegibney.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MatrixPage {
    public MatrixPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="/html/body/div/ul/li[1]/div/span/button")
    public WebElement matrixShowButton;

    @FindBy(xpath="/html/body/div/ul/li[1]/div/div/div/table/tbody/tr[1]/td[6]/button")
    public WebElement userStoryEditButton;

    @FindBy(xpath="/html/body/div/ul/li[1]/div/div/div/ul[1]/li[2]/input")
    public WebElement testCaseTextInput;

    @FindBy(xpath="/html/body/div/ul/li[1]/div/div/div/ul[1]/li[2]/button")
    public WebElement testCaseAddButton;

    @FindBy(xpath="/html/body/div/ul/li[1]/div/div/div/ul[2]/li/input")
    public WebElement defectTextInput;

    @FindBy(xpath="/html/body/div/ul/li[1]/div/div/div/ul[2]/li[2]/button")
    public WebElement defectAddButton;

    @FindBy(xpath="/html/body/div/ul/li[1]/div/div/div/button")
    public WebElement saveRequirementsButton;
}
