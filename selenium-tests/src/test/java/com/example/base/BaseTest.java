package com.example.base;

import com.example.pages.LoginPage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static com.example.utils.Utility.setUtilityDriver;

public class BaseTest {

    private WebDriver driver;
    protected BasePage basePage;
    protected LoginPage loginPage;
    private String baseUrl = System.getProperty("baseUrl", "http://localhost:3000");


    @BeforeClass
    public void setup() {
        ChromeOptions options = new  ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(10));

        if (System.getenv("GITHUB_ACTIONS") == null) {
            driver.manage().window().maximize();
        }
    }

    @BeforeMethod
    public void loadApplication() {
        driver.get(baseUrl);
        basePage = new BasePage();
        basePage.setDriver(driver);
        setUtilityDriver();
        loginPage = new LoginPage();
    }

    @AfterMethod
    public void takeFailedResultScreenshot(ITestResult testResult) {
        if (ITestResult.FAILURE == testResult.getStatus()) {
            File destination = captureScreenshot(testResult.getName());
            System.out.println("Screenshot located at: " + destination.getAbsolutePath());
        }
    }

    protected File captureScreenshot(String screenshotName) {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File source =  screenshot.getScreenshotAs(OutputType.FILE);
        File destination = getScreenshotFile(screenshotName);
        File parentDirectory = destination.getParentFile();

        if (!parentDirectory.exists() && !parentDirectory.mkdirs()) {
            throw new RuntimeException("Unable to create screenshot directory: " + parentDirectory.getAbsolutePath());
        }

        try {
            FileHandler.copy(source, destination);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return destination;
    }

    protected File getScreenshotFile(String screenshotName) {
        return new File(System.getProperty("user.dir") +
                "/resources/screenshots/(" +
                java.time.LocalDate.now() + ")" +
                screenshotName + ".png");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
