package dev.skyegibney.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ManagerHomepage extends Homepage {
    public ManagerHomepage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath="//table")
    public WebElement pendingDefectsTable;

    @FindBy(xpath="//table/tbody/tr[1]/td[3]/button")
    public WebElement defectSelectButton;

    @FindBy(xpath="//div[@id='root']/div/h4")
    public WebElement defectDescription;

    @FindBy(xpath="/html/body/div/div/input")
    public WebElement defectAssignInput;

    @FindBy(xpath="//button[text() = 'Assign']")
    public WebElement defectAssignButton;

    @FindBy(xpath="//button[contains(text(), 'Create') and contains(text(), 'Matrix')]")
    public WebElement createMatrixButton;

    @FindBy(name="title")
    public WebElement matrixTitleInput;

    @FindBy(xpath="/html/body/div/fieldset/table/tbody/tr/td[1]/input")
    public WebElement userStoryInput;

    @FindBy(xpath="/html/body/div/fieldset/table/tbody/tr/td[3]/input")
    public WebElement userStoryNoteInput;

    @FindBy(xpath="/html/body/div/fieldset/button")
    public WebElement addRequirementButton;

    @FindBy(xpath="/html/body/div/button")
    public WebElement saveMatrixButton;
}
