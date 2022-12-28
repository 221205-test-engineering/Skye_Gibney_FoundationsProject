package dev.skyegibney.stepdefinitions.matrix;

import dev.skyegibney.pages.LoginPage;
import dev.skyegibney.pages.ManagerHomepage;
import dev.skyegibney.pages.MatrixPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static dev.skyegibney.runners.MatrixTestRunner.driver;
import static org.junit.Assert.*;

public class Matrix {
    LoginPage loginPage;
    ManagerHomepage managerHomepage;
    MatrixPage matrixPage;

    @Given("The manager is logged in as a manager")
    public void theManagerIsLoggedInAsManager() {
        driver.get("https://bugcatcher-dan.coe.revaturelabs.com/?dev=25");

        loginPage = new LoginPage(driver);
        loginPage.usernameInput.sendKeys("g8tor");
        loginPage.passwordInput.sendKeys("chomp!");
        loginPage.loginButton.click();
    }

    @Given("The manager is on the home page")
    public void theManagerIsOnTheHomepage() {
        managerHomepage = new ManagerHomepage(driver);

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(managerHomepage.welcomeMessage));
    }

    @When("The manager chooses to create a new matrix")
    public void theManagerChoosesToCreateANewMatrix() {
        managerHomepage.createMatrixButton.click();
    }

    @When("The manager creates a title for the matrix")
    public void theManagerCreatesATitleForTheMatrix() {
        managerHomepage.matrixTitleInput.sendKeys("Example title");
    }

    @When("The manager adds requirements to the matrix")
    public void theManagerAddsRequirementsToTheMatrix() {
        managerHomepage.userStoryInput.sendKeys("");
    }

    @When("The manager saves the matrix")
    public void theManagerSavesTheMatrix() {
        managerHomepage.saveMatrixButton.click();
    }

    @Then("An alert with a success message should appear")
    public void anAlertWithASuccessMessageShouldAppear() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.alertIsPresent());

        String alertMessage = driver.switchTo().alert().getText();
        assertTrue(alertMessage.contains("created"));

        driver.switchTo().alert().accept();
    }

    @Given("The manager is on the matrix homepage")
    public void theManagerIsOnTheMatrixHomepage() {
        managerHomepage.matricesLink.click();

        matrixPage = new MatrixPage(driver);

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(matrixPage.matrixShowButton));
    }

    @Given("The manager has selected the matrix")
    public void theManagerHasSelectedTheMatrix() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(matrixPage.matrixShowButton));

        matrixPage.matrixShowButton.click();
    }

    @When("The manager adds a defect")
    public void theManagerAddsADefect() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(matrixPage.userStoryEditButton));

        matrixPage.userStoryEditButton.click();
        matrixPage.userStoryEditButton.click();
        matrixPage.defectTextInput.sendKeys("903");
        matrixPage.defectAddButton.click();
    }

    @When("The manager confirms their changes")
    public void theManagerConfirmsTheirChanges() {
        matrixPage.saveRequirementsButton.click();
    }

    @Then("Then the matrix should saved")
    public void thenTheMatrixShouldSaved() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.alertIsPresent());

        assertEquals("Matrix Saved", driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
    }

    @When("The manager adds a Test Cases")
    public void theManagerAddsATestCases() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(matrixPage.userStoryEditButton));

        matrixPage.userStoryEditButton.click();
        matrixPage.userStoryEditButton.click();
        matrixPage.testCaseTextInput.sendKeys("803");
        matrixPage.testCaseAddButton.click();
    }
}
