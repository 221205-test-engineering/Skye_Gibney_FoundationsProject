package dev.skyegibney.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TesterHomepage extends Homepage {
    public TesterHomepage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath="/html/body/div[1]/ul")
    public WebElement defectList;

    @FindBy(xpath="/html/body/div[1]/ul/li[1]/div/span/p/b[2]")
    public WebElement defectStatus;

    @FindBy(xpath="/html/body/div[1]/ul/li[1]/div/div/div/div[1]/span/button")
    public WebElement changeStatusButton;

    @FindBy(xpath="/html/body/div[1]/ul/li[1]/div/div/div/div[1]/div/div/button[1]")
    public WebElement acceptedButton;

    @FindBy(xpath="/html/body/div[1]/ul/li[1]/div/div/div/div[1]/div/div/button[2]")
    public WebElement rejectedButton;
}
