package com.example.tests.screenshot;

import com.example.base.BaseTest;
import org.testng.annotations.Test;

public class CaptureFailedScreenshotTest extends BaseTest {

    @Test
    public void clickAFakeElement() {
        var formPage = loginPage.login("admin", "admin123");
        formPage.clickFakeElement();
    }
}
