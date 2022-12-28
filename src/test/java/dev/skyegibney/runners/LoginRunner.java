package dev.skyegibney.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import dev.skyegibney.pages.Homepage;
import dev.skyegibney.pages.LoginPage;

@CucumberOptions(
        features="src/test/java/dev/skyegibney/features/login",
        glue="dev.skyegibney.stepdefinitions.login"
)
@RunWith(Cucumber.class)
public class LoginRunner {
    public static WebDriver driver;
    public static LoginPage loginPage;
    public static Homepage homepage;

    @BeforeClass
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        loginPage = new LoginPage(driver);
        homepage = new Homepage(driver);
    }

    @AfterClass
    public static void cleanup() {
        driver.quit();
    }
}
