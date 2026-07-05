package com.example.tests.screenshot;

import com.example.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class CaptureFailedScreenshotTest extends BaseTest {

    @Test
    public void clickAFakeElementCapturesPngScreenshot() {
        var formPage = loginPage.login("admin", "admin123");
        String screenshotName = "clickAFakeElementCapturesPngScreenshot";

        try {
            formPage.clickFakeElement();
            Assert.fail("Expected clicking a fake element to fail.");
        } catch (RuntimeException exception) {
            File screenshot = captureScreenshot(screenshotName);

            Assert.assertTrue(screenshot.exists(), "Expected a screenshot file to be created.");
            Assert.assertTrue(screenshot.getName().endsWith(".png"), "Expected screenshot to be a PNG file.");
            Assert.assertTrue(screenshot.length() > 0, "Expected screenshot file to contain image data.");
        }
    }
}
