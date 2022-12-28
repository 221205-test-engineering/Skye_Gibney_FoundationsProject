package dev.skyegibney.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DefectReporter {
    public DefectReporter(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="/html/body/div[1]/form/input[1]")
    public WebElement dateInput;

    @FindBy(xpath="/html/body/div[1]/form/textarea[1]")
    public WebElement descriptionInput;

    @FindBy(xpath="/html/body/div[1]/form/textarea[2]")
    public WebElement stepsInput;

    @FindBy(xpath="/html/body/div[1]/form/input[2]")
    public WebElement severitySlider;

    @FindBy(xpath="/html/body/div[1]/form/input[3]")
    public WebElement prioritySlider;

    @FindBy(xpath="/html/body/div[1]/form/button")
    public WebElement reportButton;

    @FindBy(xpath="/html/body/div[3]/div/div/h4")
    public WebElement modalText;

    @FindBy(xpath="/html/body/div[3]/div/div/button")
    public WebElement modalCloseButton;
}
