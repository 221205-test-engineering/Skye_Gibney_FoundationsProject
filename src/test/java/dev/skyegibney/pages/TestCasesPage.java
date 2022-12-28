package dev.skyegibney.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TestCasesPage {
    public TestCasesPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(name="desc")
    public WebElement descriptionInput;

    @FindBy(name="steps")
    public WebElement stepsInput;

    @FindBy(xpath="//button[@type='submit']")
    public WebElement submitButton;

    @FindBy(xpath="//table/tbody")
    public WebElement testCaseTableBody;

    public int getTestCaseCount() {
        return testCaseTableBody.findElements(By.tagName("tr")).size();
    }

    public WebElement getLastRowTestCase() {
        return testCaseTableBody.findElement(By.xpath("./tr[last()]"));
    }
}
