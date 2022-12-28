package dev.skyegibney.stepdefinitions.navigation;

import dev.skyegibney.pages.LoginPage;
import dev.skyegibney.pages.ManagerHomepage;
import io.cucumber.core.exception.CucumberException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.*;
import static dev.skyegibney.runners.NavigationRunner.*;

public class Navigation {
    LoginPage loginPage;
    ManagerHomepage managerHomepage;

    @Then("The manager should see links for Matrices, Test Cases, Defect Reporting and Defect Overview")
    public void theManagerShouldSeeLinksForMatricesTestCasesDefectReportingAndDefectOverview() {
        assertTrue(managerHomepage.matricesLink.isDisplayed());
        assertTrue(managerHomepage.testCasesLink.isDisplayed());
        assertTrue(managerHomepage.reportADefectLink.isDisplayed());
        assertTrue(managerHomepage.defectOverviewLink.isDisplayed());
    }

    @When("The manager clicks on Matrices")
    public void theManagerClicksOnMatrices() {
        managerHomepage.matricesLink.click();
    }

    @Then("The title of the page should be Matrix Page")
    public void theTitleOfThePageShouldBeMatrixPage() {
        assertTrue(driver.getTitle().contains("Matrix"));
    }

    @When("The manager clicks the browser back button")
    public void theManagerClicksTheBrowserBackButton() {
        driver.navigate().back();
    }

    @Then("The manager should be on the home page and the title of page is Home")
    public void theManagerShouldBeOnTheHomePageAndTheTitleOfPageIsHome() {
        assertTrue(driver.getTitle().contains("Home"));
    }

    @When("The manager clicks on Test Cases")
    public void theManagerClicksOnTestCases() {
        managerHomepage.testCasesLink.click();
    }

    @When("The manager clicks on {string}")
    public void theManagerClicksOn(String arg0) {
        WebElement link = switch (arg0) {
            case "Matrices" -> managerHomepage.matricesLink;
            case "Test Cases" -> managerHomepage.testCasesLink;
            case "Report a Defect" -> managerHomepage.reportADefectLink;
            case "Defect Overview" -> managerHomepage.defectOverviewLink;
            default -> throw new CucumberException("Link does not exist");
        };

        link.click();
    }

    @Then("The title of page should be {string}")
    public void theTitleOfPageShouldBe(String arg0) {
        assertEquals(arg0, driver.getTitle());
    }

    @Given("The manager is logged in as a manager")
    public void theManagerIsLoggedInAsAManager() {
        driver.get("https://bugcatcher-dan.coe.revaturelabs.com/?dev=25");

        loginPage = new LoginPage(driver);

        loginPage.usernameInput.sendKeys("g8tor");
        loginPage.passwordInput.sendKeys("chomp!");
        loginPage.loginButton.click();

        managerHomepage = new ManagerHomepage(driver);

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(managerHomepage.welcomeMessage));
    }

    @Given("The manager is on the home page")
    public void theManagerIsOnTheHomePage() {
        assertTrue(driver.getTitle().contains("Manager"));
    }
}
