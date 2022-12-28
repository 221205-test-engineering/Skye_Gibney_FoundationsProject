package dev.skyegibney.runners;

import dev.skyegibney.pages.Homepage;
import dev.skyegibney.pages.LoginPage;
import dev.skyegibney.pages.ManagerHomepage;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Cucumber.class)
@CucumberOptions(
        features="src/test/java/dev/skyegibney/features/navigation",
        glue="dev.skyegibney.stepdefinitions.navigation"
)
public class NavigationRunner {
    public static WebDriver driver;

    @BeforeClass
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterClass
    public static void cleanup() {
        driver.quit();
    }
}
