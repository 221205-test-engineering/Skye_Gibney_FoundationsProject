package dev.skyegibney.stepdefinitions.testcases;

import dev.skyegibney.pages.*;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static dev.skyegibney.runners.TestCaseRunner.*;
import static org.junit.Assert.*;

public class TestCases {
    LoginPage loginPage;
    Homepage homepage;
    TestCasesPage testCasesPage;
    TestCasesModal modal;
    TestCaseEditor testCaseEditor;

    String testCaseId;
    int testCaseCount = 0;

    String previousDescription;
    String previousSteps;
    String previousAutomated;
    String previousPerformedBy;
    String previousTestResult;
    String previousSummary;

    @Given("The tester is on the test case dashboard")
    public void theTesterIsOnTheTestCaseDashboard() {
        driver.get("https://bugcatcher-dan.coe.revaturelabs.com/?dev=25");

        loginPage = new LoginPage(driver);

        loginPage.usernameInput.sendKeys("ryeGuy");
        loginPage.passwordInput.sendKeys("coolbeans");
        loginPage.loginButton.click();

        homepage = new Homepage(driver);

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(homepage.welcomeMessage));

        homepage.testCasesLink.click();

        testCasesPage = new TestCasesPage(driver);

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(testCasesPage.testCaseTableBody));
    }

    @When("The tester types Description into input with")
    public void theTesterTypesDescriptionIntoInputWith(String description) {
        testCasesPage.descriptionInput.sendKeys(description);
    }

    @When("The tester types Steps into input with")
    public void theTesterTypesStepsIntoInputWith(String steps) {
        testCasesPage.stepsInput.sendKeys(steps);
    }

    @When("The tester presses the submit button")
    public void theTesterPressesTheSubmitButton() {
        testCaseCount = testCasesPage.getTestCaseCount();
        testCasesPage.submitButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//table/tbody/tr[" + (testCaseCount + 1) + "]")));
    }

    @Then("The test case should appear at the bottom of the table")
    public void theTestCaseShouldAppearAtTheBottomOfTheTable() {
        int newTestCaseCount = testCasesPage.getTestCaseCount();
        assertNotEquals(testCaseCount, newTestCaseCount);
    }

    @Then("The test case result should say UNEXECUTED")
    public void theTestCaseResultShouldSayUNEXECUTED() {
        WebElement newestTestCase = testCasesPage.testCaseTableBody.findElement(By.xpath("./tr[last()]"));
        WebElement newestTestCaseResult = newestTestCase.findElement(By.xpath("./td[3]"));

        assertEquals("UNEXECUTED", newestTestCaseResult.getText());
    }

    @When("The tester presses on details")
    public void theTesterPressesOnDetails() {
        WebElement lastRow = testCasesPage.getLastRowTestCase();
        WebElement detailsButton = lastRow.findElement(By.xpath("./td[4]"));
        WebElement testCaseId = lastRow.findElement(By.xpath("./td[1]"));

        this.testCaseId = testCaseId.getText();
        detailsButton.click();
    }

    @Then("A test case modal should appear showing the case ID")
    public void aTestCaseModalShouldAppearShowingTheCaseID() {
        modal = new TestCasesModal(driver);
        assertTrue(modal.testCaseHeading.getText().contains(testCaseId));
    }

    @Then("The performed by field should say No One")
    public void thePerformedByFieldShouldSayNoOne() {
        assertEquals("No One", modal.performedBy.getText());
    }

    @When("The tester presses the close buttton")
    public void theTesterPressesTheCloseButtton() {
        modal.closeButton.click();
    }

    @Then("The Modal Should be closed")
    public void theModalShouldBeClosed() {
        List<WebElement> elements = driver.findElements(By.xpath("/html/body/div[3]/div/div"));
        assertEquals(0, elements.size());
    }

    @When("The Tester clicks on edit within the modal")
    public void theTesterClicksOnEditWithinTheModal() {
        modal.editButton.click();
    }

    @Then("The Tester should be on the case editor for that case")
    public void theTesterShouldBeOnTheCaseEditorForThatCase() {
        testCaseEditor = new TestCaseEditor(driver);

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(testCaseEditor.editButton));

        assertTrue(driver.getCurrentUrl().contains("caseeditor/" + testCaseId));
    }

    @When("The tester clicks on the edit button")
    public void theTesterClicksOnTheEditButton() {
        testCaseEditor.editButton.click();

        previousDescription = testCaseEditor.descriptionInput.getText();
        previousSteps = testCaseEditor.stepsInput.getText();
        previousAutomated = testCaseEditor.automatedCheckbox.getAttribute("value");
        previousPerformedBy = testCaseEditor.performedByDropdown.getAttribute("value");
        previousTestResult = testCaseEditor.testResultDropdown.getAttribute("value");
        previousSummary = testCaseEditor.summaryTextArea.getText();
    }

    @When("The tester types in {string} into the description text area")
    public void theTesterTypesInIntoTheDescriptionTextArea(String arg0) {
        testCaseEditor.descriptionInput.sendKeys(arg0);
    }

    @When("The tester types in {string} into the steps text area")
    public void theTesterTypesInIntoTheStepsTextArea(String arg0) {
        testCaseEditor.stepsInput.sendKeys(arg0);
    }

    @When("The tester clicks on the automated check mark")
    public void theTesterClicksOnTheAutomatedCheckMark() {
        testCaseEditor.automatedCheckbox.click();
    }

    @When("The tester selects {string} for performed from drop down")
    public void theTesterSelectsForPerformedFromDropDown(String arg0) {
        Select tester = new Select(testCaseEditor.performedByDropdown);
        tester.selectByValue(arg0);
    }

    @When("The tester selects {string} for test result from drop down")
    public void theTesterSelectsForTestResultFromDropDown(String arg0) {
        Select result = new Select(testCaseEditor.testResultDropdown);
        result.selectByValue(arg0);
    }

    @When("The tester types in {string} into the summary text area")
    public void theTesterTypesInIntoTheSummaryTextArea(String arg0) {
        testCaseEditor.summaryTextArea.sendKeys(arg0);
    }

    @When("The tester clicks save on test case page")
    public void theTesterClicksSaveOnTestCasePage() {
        testCaseEditor.saveButton.click();
    }

    @Then("A confirmation prompt should appear")
    public void aConfirmationPromptShouldAppear() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.alertIsPresent());

        assertEquals("Are you sure you want to update the test case?",
                driver.switchTo().alert().getText());
    }

    @When("The tester clicks on Ok")
    public void theTesterClicksOnOk() {
        driver.switchTo().alert().accept();
    }

    @Then("An alert says the test case has been saved")
    public void anAlertSaysTheTestCaseHasBeenSaved() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.alertIsPresent());

        assertEquals("Test Case has been Saved",
                driver.switchTo().alert().getText());

        driver.switchTo().alert().accept();
    }

    @When("The tester clicks on the reset button")
    public void theTesterClicksOnTheResetButton() {
        testCaseEditor.resetButton.click();
    }

    @Then("The fields should be populated to their old values")
    public void theFieldsShouldBePopulatedToTheirOldValues() {
        assertEquals(previousDescription, testCaseEditor.descriptionInput.getText());
        assertEquals(previousSteps, testCaseEditor.stepsInput.getText());

        if (previousAutomated.equals("on")) {
            assertEquals("YES", testCaseEditor.automatedText.getText());
        }
        else {
            assertEquals("NO", testCaseEditor.automatedText.getText());
        }

        assertEquals(previousPerformedBy, testCaseEditor.performedByText.getText());
        assertEquals(previousTestResult, testCaseEditor.testResultText.getText());
        assertEquals(previousSummary, testCaseEditor.summaryTextArea.getText());
    }
}
