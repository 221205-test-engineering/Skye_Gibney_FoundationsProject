package dev.skyegibney.stepdefinitions.login;

import dev.skyegibney.pages.Homepage;
import dev.skyegibney.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import dev.skyegibney.runners.LoginRunner;

import static dev.skyegibney.runners.LoginRunner.*;
import static org.junit.Assert.*;

import java.time.Duration;

public class Login {
    LoginPage loginPage;
    Homepage homepage;

    @Given("The employee is on the login page")
    public void theEmployeeIsOnTheLoginPage() {
        driver.get("https://bugcatcher-dan.coe.revaturelabs.com/?dev=25");
        loginPage = new LoginPage(driver);
    }

    @When("The employee types {string} into username input")
    public void theEmployeeTypesIntoUsernameInput(String arg0) {
        loginPage.usernameInput.sendKeys(arg0);
    }

    @When("The employee types {string} into password input")
    public void theEmployeeTypesIntoPasswordInput(String arg0) {
        loginPage.passwordInput.sendKeys(arg0);
    }

    @When("The employee clicks on the login button")
    public void theEmployeeClicksOnTheLoginButton() {
        loginPage.loginButton.click();
    }

    @Then("the employee should be on the {string} page")
    public void theEmployeeShouldBeOnThePage(String arg0) {
        homepage = new Homepage(driver);

        // Wait for page to change
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(homepage.welcomeMessage));

         assertEquals(arg0 + " Home", driver.getTitle());
    }

    @Then("The employee should see their name {string} {string} on the home page")
    public void theEmployeeShouldSeeTheirNameOnTheHomePage(String arg0, String arg1) {
        Assert.assertTrue(homepage.welcomeMessage.getText().contains(arg0));
        Assert.assertTrue(homepage.welcomeMessage.getText().contains(arg1));
    }

    @When("The employee types in g8tor into the username input")
    public void theEmployeeTypesInG8torIntoTheUsernameInput() {
        loginPage.usernameInput.sendKeys("g8tor");
    }

    @When("The employee types in chomp!! into the password input")
    public void theEmployeeTypesInChompIntoThePasswordInput() {
        loginPage.passwordInput.sendKeys("chomp!!");
    }

    @Then("The employee should see an alert saying they have the wrong password")
    public void theEmployeeShouldSeeAnAlertSayingTheyHaveTheWrongPassword() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        assertNotNull(wait.until(ExpectedConditions.alertIsPresent()));

        String alertMessage = LoginRunner.driver.switchTo().alert().getText();
        assertEquals("Wrong password for User", alertMessage);

        LoginRunner.driver.switchTo().alert().accept();
    }

    @When("The employee types in sicEmDawgs into the username input")
    public void theEmployeeTypesInSicEmDawgsIntoTheUsernameInput() {
        loginPage.usernameInput.sendKeys("sicEmDawgs");
    }

    @When("The employee types in natchamps into the password input")
    public void theEmployeeTypesInNatchampsIntoThePasswordInput() {
        loginPage.passwordInput.sendKeys("natchamps");
    }

    @Then("The employee should see an alert saying no user with that username found")
    public void theEmployeeShouldSeeAnAlertSayingNoUserWithThatUsernameFound() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        assertNotNull(wait.until(ExpectedConditions.alertIsPresent()));

        String alertMessage = driver.switchTo().alert().getText();
        assertEquals("Username not found", alertMessage);

        LoginRunner.driver.switchTo().alert().accept();
    }
}
