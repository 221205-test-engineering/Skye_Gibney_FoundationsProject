package dev.skyegibney.stepdefinitions.defect;

import dev.skyegibney.pages.*;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.it.Date;
import org.junit.rules.Timeout;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static dev.skyegibney.runners.DefectRunner.driver;
import static org.junit.Assert.*;

public class Defect {
    LoginPage loginPage;
    ManagerHomepage managerHomepage;
    TesterHomepage testerHomepage;
    DefectReporter defectReporter;

    int numDefects;
    String oldStatus;

    @Given("The manager is logged in as a manager")
    public void theManagerIsLoggedInAsAManager() {
        driver.get("https://bugcatcher-dan.coe.revaturelabs.com/?dev=25");

        loginPage = new LoginPage(driver);

        loginPage.usernameInput.sendKeys("g8tor");
        loginPage.passwordInput.sendKeys("chomp!");
        loginPage.loginButton.click();
    }

    @Given("The manager is on the home page")
    public void theManagerIsOnTheHomePage() {
        managerHomepage = new ManagerHomepage(driver);

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(managerHomepage.welcomeMessage));
    }

    @Then("The manager should see pending defects")
    public void theManagerShouldSeePendingDefects() {
        assertTrue(managerHomepage.pendingDefectsTable.isDisplayed());
    }

    @When("The manager clicks on the select button for a defect")
    public void theManagerClicksOnTheSelectButtonForADefect() {
        managerHomepage.defectSelectButton.click();
    }

    @Then("The defect description should appear in bold")
    public void theDefectDescriptionShouldAppearInBold() {
        assertTrue(managerHomepage.defectDescription.isDisplayed());

        String fontWeight = managerHomepage.defectDescription.getCssValue("font-weight");
        assertTrue(fontWeight.equals("bold") || fontWeight.equals("700"));
    }

    @When("The manager selects a tester from the drop down list")
    public void theManagerSelectsATesterFromTheDropDownList() {
        Select testers = new Select(managerHomepage.defectAssignInput);
        testers.selectByIndex(0);
    }

    @When("The manager clicks assign")
    public void theManagerClicksAssign() {
        numDefects = managerHomepage.pendingDefectsTable.findElements(By.xpath("./tbody/tr")).size();
        managerHomepage.defectAssignButton.click();
    }

    @Then("The defect should disappear from the list")
    public void theDefectShouldDisappearFromTheList() {
        assertNotEquals(numDefects, managerHomepage.pendingDefectsTable.findElements(By.xpath("./tbody/tr")).size());
    }

    @Given("The tester is on the Home Page")
    public void theTesterIsOnTheHomePage() {
        driver.get("https://bugcatcher-dan.coe.revaturelabs.com/?dev=25");

        loginPage = new LoginPage(driver);

        loginPage.usernameInput.sendKeys("ryeGuy");
        loginPage.passwordInput.sendKeys("coolbeans");
        loginPage.loginButton.click();

        testerHomepage = new TesterHomepage(driver);

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(testerHomepage.welcomeMessage));
    }

    @Then("The tester can can see only defects assigned to them")
    public void theTesterCanCanSeeOnlyDefectsAssignedToThem() {
        List<WebElement> clickers = testerHomepage.defectList.findElements(By.xpath("./li"));
        List<WebElement> performedBys = testerHomepage.defectList.findElements(By.xpath("./li/div/div/div/p[5]"));

        for (var clicker: clickers) {
            clicker.click();
        }

        for (var performedBy: performedBys) {
            new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.elementToBeClickable(performedBy));

            assertTrue(performedBy.getAttribute("innerHTML").contains("ryeGuy"));
        }
    }

    @When("The tester changes to defect to any status")
    public void theTesterChangesToDefectToAnyStatus() {
        oldStatus = testerHomepage.defectStatus.getText();

        testerHomepage.changeStatusButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.elementToBeClickable(testerHomepage.acceptedButton));

        if (!oldStatus.equals("Accepted")) {
            testerHomepage.acceptedButton.click();

            new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.textToBePresentInElement(testerHomepage.defectStatus, "Accepted"));
        }
        else {
            testerHomepage.rejectedButton.click();

            new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.textToBePresentInElement(testerHomepage.defectStatus, "Rejected"));
        }
    }

    @Then("The tester should see the defect has a different status")
    public void theTesterShouldSeeTheDefectHasADifferentStatus() {
        assertNotEquals(oldStatus, testerHomepage.defectStatus.getText());
    }

    @Given("The employee is on the Defect Reporter Page")
    public void theEmployeeIsOnTheDefectReporterPage() {
        driver.get("https://bugcatcher-dan.coe.revaturelabs.com/?dev=25");

        loginPage = new LoginPage(driver);
        loginPage.usernameInput.sendKeys("ryeGuy");
        loginPage.passwordInput.sendKeys("coolbeans");
        loginPage.loginButton.click();

        testerHomepage = new TesterHomepage(driver);

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(testerHomepage.reportADefectLink));

        testerHomepage.reportADefectLink.click();

        defectReporter = new DefectReporter(driver);

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(defectReporter.dateInput));
    }

    @When("The employee selects todays date")
    public void theEmployeeSelectsTodaysDate() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMddyyyy");
        String date = LocalDate.now().format(dateFormatter);
        defectReporter.dateInput.sendKeys(date);
    }

    @When("The employee types in description with")
    public void theEmployeeTypesInDescriptionWith(String description) {
        defectReporter.descriptionInput.sendKeys(description);
    }

    @When("The employee types in Steps with")
    public void theEmployeeTypesInStepsWith(String steps) {
        defectReporter.stepsInput.sendKeys(steps);
    }

    @When("The employee selects high priority")
    public void theEmployeeSelectsHighPriority() {

    }

    @When("The employee selects low severity")
    public void theEmployeeSelectsLowSeverity() {
        defectReporter.severitySlider.sendKeys(Keys.ARROW_LEFT);
        defectReporter.severitySlider.sendKeys(Keys.ARROW_LEFT);
    }

    @When("The employee clicks the report button")
    public void theEmployeeClicksTheReportButton() {
        defectReporter.reportButton.click();
    }

    @Then("No confirmation dialog appears")
    public void noConfirmationDialogAppears() {
        boolean isAlertPresent = false;

        try {
            new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.alertIsPresent());

            isAlertPresent = true;
            driver.switchTo().alert().accept();
        } catch (TimeoutException e) {
            isAlertPresent = false;
        }

        assertFalse(isAlertPresent);
    }

    @When("The employee selects {string} priority")
    public void theEmployeeSelectsPriority(String arg0) {
        switch (arg0) {
            case "Medium" -> {
                defectReporter.prioritySlider.sendKeys(Keys.ARROW_LEFT);
            }
            case "LOW" -> {
                defectReporter.prioritySlider.sendKeys(Keys.ARROW_LEFT);
                defectReporter.prioritySlider.sendKeys(Keys.ARROW_LEFT);
            }
        }
    }

    @When("The employee selects {string} severity")
    public void theEmployeeSelectsSeverity(String arg0) {
        switch (arg0) {
            case "Medium" -> {
                defectReporter.severitySlider.sendKeys(Keys.ARROW_LEFT);
            }
            case "LOW" -> {
                defectReporter.severitySlider.sendKeys(Keys.ARROW_LEFT);
                defectReporter.severitySlider.sendKeys(Keys.ARROW_LEFT);
            }
        }
    }

    @Then("There should be a confirmation box")
    public void thereShouldBeAConfirmationBox() {
        boolean isAlertPresent = false;

        try {
            new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.alertIsPresent());

            isAlertPresent = true;
        } catch (TimeoutException e) {
            isAlertPresent = false;
        }

        assertTrue(isAlertPresent);
    }

    @When("The employee clicks Ok")
    public void theEmployeeClicksOk() {
        driver.switchTo().alert().accept();
    }

    @Then("A modal should appear with a Defect ID")
    public void aModalShouldAppearWithADefectID() {
        boolean modalAppeared = false;

        try {
            new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.elementToBeClickable(defectReporter.modalText));
            modalAppeared = true;
        } catch (TimeoutException e) {
            modalAppeared = false;
        }

        assertTrue(modalAppeared);
    }

    @When("The employee clicks close")
    public void theEmployeeClicksClose() {
        defectReporter.modalCloseButton.click();
    }

    @Then("The modal should disappear")
    public void theModalShouldDisappear() {
        boolean modalAppeared = false;

        try {
            new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.elementToBeClickable(defectReporter.modalText));
            modalAppeared = true;
        } catch (TimeoutException e) {
            modalAppeared = false;
        }

        assertFalse(modalAppeared);
    }
}
