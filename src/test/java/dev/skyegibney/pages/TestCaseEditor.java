package dev.skyegibney.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TestCaseEditor {
    public TestCaseEditor(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//body/div/button[text()='Edit']")
    public WebElement editButton;

    @FindBy(xpath="//body/div/fieldset[1]/textarea[1]")
    public WebElement descriptionInput;

    @FindBy(xpath="//body/div/fieldset[1]/textarea[2]")
    public WebElement stepsInput;

    @FindBy(xpath="/html/body/div/fieldset[1]/input")
    public WebElement automatedCheckbox;

    @FindBy(xpath="/html/body/div/fieldset[1]/p[1]")
    public WebElement automatedText;

    @FindBy(xpath="/html/body/div/fieldset[1]/select")
    public WebElement performedByDropdown;

    @FindBy(xpath="/html/body/div/fieldset[1]/p[2]")
    public WebElement performedByText;

    @FindBy(xpath="/html/body/div/fieldset[2]/select")
    public WebElement testResultDropdown;

    @FindBy(xpath="/html/body/div/fieldset[2]/p")
    public WebElement testResultText;

    @FindBy(xpath="/html/body/div/fieldset[2]/textarea")
    public WebElement summaryTextArea;

    @FindBy(xpath="//body/div/button[text()='Reset']")
    public WebElement resetButton;

    @FindBy(xpath="/html/body/div/button[2]")
    public WebElement saveButton;
}
