package dev.skyegibney.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@CucumberOptions(
        features="src/test/java/dev/skyegibney/features/defect",
        glue="dev.skyegibney.stepdefinitions.defect"
)
@RunWith(Cucumber.class)
public class DefectRunner {
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
