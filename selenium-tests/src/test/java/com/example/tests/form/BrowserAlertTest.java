package com.example.tests.form;

import com.example.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.example.utils.SwitchToUtility.acceptAlert;
import static com.example.utils.SwitchToUtility.getAlertText;

public class BrowserAlertTest extends BaseTest {

    @Test
    public void browserAlertTest(){
        var formsPage = loginPage.login("admin", "admin123");
        if (!formsPage.isBrowserAlertCheckBoxSelected()) {
            formsPage.clickBrowserAlertCheckBox();
        }
        formsPage.clickSubmitButton();
        String expectedAlertText = "Form submitted successfully";
        Assert.assertEquals(getAlertText(), expectedAlertText,
                "\n Actual and Expected Messages Do Not Match \n");
        acceptAlert();
    }
}
