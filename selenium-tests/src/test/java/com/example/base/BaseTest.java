package com.example.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static com.example.utils.Utility.setUtilityDriver;

public class BaseTest {

    private WebDriver driver;
    protected BasePage basePage;
    protected LoginPage loginPage;
    private String baseUrl = "http://localhost:3000";


    @BeforeClass
    public void setup() {
        // Setup Chrome driver and maximize window on load
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void loadApplication() {
        driver.get(baseUrl);
        basePage = new BasePage();
        basePage.setDriver(driver);
        setUtilityDriver();
        loginPage = new LoginPage();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}
