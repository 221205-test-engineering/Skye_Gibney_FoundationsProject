package dev.skyegibney.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

public class Homepage {
    public Homepage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="/html/body/div/nav/p")
    public WebElement welcomeMessage;

    @FindBy(xpath="//nav/a[1]")
    public WebElement matricesLink;

    @FindBy(xpath="//nav/a[2]")
    public WebElement testCasesLink;

    @FindBy(xpath="//nav/a[3]")
    public WebElement reportADefectLink;

    @FindBy(xpath="//nav/a[4]")
    public WebElement defectOverviewLink;
}
